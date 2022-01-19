package org.ageseries.securityage.data

import net.minecraft.nbt.CompoundTag


class LocksetBitting {
    var descriptor: BittingDescriptor? = null
    var pinSets: MutableList<MutableSet<Int>>? = null

    val DEFAULT_BITTING: LocksetBitting = LocksetBitting(BittingDescriptor.WOOD, intArrayOf(0), intArrayOf(0), intArrayOf(0), intArrayOf(0))

    fun LocksetBitting(descriptor: BittingDescriptor, vararg pinSetArrays: IntArray): LocksetBitting {
        this.descriptor = descriptor
        assert(pinSetArrays.size == descriptor.positions)
        pinSets = ArrayList(descriptor.positions!!)
        for (i in 0 until descriptor.positions!!) {
            val set: MutableSet<Int> = HashSet()
            for (j in pinSetArrays[i].indices) {
                set.add(pinSetArrays[i][j])
            }
            (pinSets as ArrayList<MutableSet<Int>>).add(set)
        }
        return this
    }

    fun LocksetBitting(descriptor: BittingDescriptor?, pinSets: MutableList<MutableSet<Int>>?) {
        this.descriptor = descriptor
        this.pinSets = pinSets
    }

    fun LocksetBitting(descriptor: BittingDescriptor) {
        val ps: MutableList<MutableSet<Int>> = ArrayList(descriptor.positions!!)
        for (i in 0 until descriptor.positions!!) {
            val set: MutableSet<Int> = HashSet()
            ps.add(set)
        }
        this.descriptor = descriptor
        pinSets = ps
    }

    fun getPinSets(idx: Int): MutableSet<Int> {
        return if (idx < 0 || idx >= descriptor!!.positions!!) HashSet() else pinSets!![idx]
    }

    fun getPinSetsAsArray(idx: Int): IntArray? {
        val ps: Set<Int> = getPinSets(idx)
        val ints = IntArray(ps.size)
        val iter = ps.iterator()
        var i = 0
        while (iter.hasNext()) ints[i++] = iter.next()
        return ints
    }

    fun addPin(idx: Int, pin: Int) {
        if (idx < 0 || idx >= descriptor!!.positions!!) return
        if (pin < 0 || pin >= descriptor!!.settings!!) return
        getPinSets(idx).add(pin)
    }

    fun removePin(idx: Int, pin: Int) {
        if (idx < 0 || idx >= descriptor!!.positions!!) return
        if (pin < 0 || idx >= descriptor!!.settings!!) return
        getPinSets(idx).remove(pin)
    }

    fun fits(key: KeyBitting): Boolean {
        if (key.overrides) return true
        if (descriptor !== key.descriptor) return false
        for (i in 0 until descriptor.positions!!) {
            if (!pinSets!![i].contains(key.getPin(i))) return false
        }
        return true
    }

    fun toNBT(): CompoundTag? {
        val tag = CompoundTag()
        tag.setTag("desc", descriptor!!.toNBT())
        for (i in 0 until descriptor.positions!!) {
            tag.setIntArray("pin$i", getPinSetsAsArray(i))
        }
        return tag
    }

    fun fromNBT(tag: CompoundTag): LocksetBitting? {
        val desc: BittingDescriptor = BittingDescriptor.fromNBT(tag.getCompoundTag("desc"))
        val list: MutableList<MutableSet<Int>> = ArrayList(desc.positions!!)
        for (i in 0 until desc.positions!!) {
            val ps: MutableSet<Int> = HashSet()
            val psi: IntArray = tag.getIntArray("pin$i")
            for (j in psi.indices) {
                ps.add(psi[j])
            }
            list.add(ps)
        }
        return LocksetBitting(desc, list)
    }

    override fun toString(): String {
        return "LocksetBitting{" +
                "descriptor=" + descriptor +
                ", pinSets=" + pinSets +
                '}'
    }
}