package org.ageseries.securityage.data

import net.minecraft.nbt.CompoundTag
import java.util.*

class KeyBitting {
    public var descriptor: BittingDescriptor? = null
    public var pins: IntArray? = null
    public var overrides = false

    val OVERRIDE_BITTING: KeyBitting = KeyBitting(true)

    private fun KeyBitting(unused: Boolean): KeyBitting {
        descriptor = BittingDescriptor.WOOD
        pins = IntArray(descriptor!!.positions!!)
        overrides = true
        return this
    }

    fun KeyBitting(descriptor: BittingDescriptor?, pins: IntArray): KeyBitting {
        this.descriptor = descriptor
        this.pins = pins
        return this
    }

    fun KeyBitting(descriptor: BittingDescriptor): KeyBitting {
        KeyBitting(descriptor, IntArray(descriptor.positions!!))
        return this
    }

    fun getPin(idx: Int): Int? {
        return if (idx < 0 || idx >= descriptor!!.positions) -1 else pins?.get(idx)
    }

    fun setPin(idx: Int, setting: Int) {
        var setting = setting
        if (idx < 0 || idx >= descriptor!!.positions!!) return
        if (setting < 0) setting = 0
        if (setting >= descriptor!!.settings!!) setting = descriptor!!.settings!! - 1
        pins?.set(idx, setting)
    }

    fun fits(lock: LocksetBitting): Boolean {
        return lock.fits(this)
    }

    fun toNBT(): CompoundTag? {
        val tag = CompoundTag()
        tag.put("desc", descriptor!!.toNBT())
        tag.putIntArray("pins", pins)
        if (overrides) tag.putBoolean("overrides", true)
        return tag
    }

    fun fromNBT(tag: CompoundTag): KeyBitting {
        val desc = BittingDescriptor.fromNBT(tag.getCompound("desc"))
        val pins = tag.getIntArray("pins")
        val instance = KeyBitting(desc, pins)
        if (tag.get("overrides") != null && tag.getBoolean("overrides")) instance.overrides = true
        return instance
    }

    override fun toString(): String {
        return "KeyBitting{" +
                "descriptor=" + descriptor +
                ", pins=" + Arrays.toString(pins) +
                ", overrides=" + overrides +
                '}'
    }
}