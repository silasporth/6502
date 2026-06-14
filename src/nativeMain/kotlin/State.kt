const val STACK_POINTER_OFFSET: SWord = 0x0100u
const val NON_MASKABLE_INTERRUPT_VECTOR: SWord = 0xFFFAu
const val RESET_VECTOR: SWord = 0xFFFCu
const val INTERRUPT_VECTOR: SWord = 0xFFFEu

class State {

    var totalCycles: UInt = 0u
        private set

    var accumulator: SByte = 0u
        private set
    var indexRegisterX: SByte = 0u
        private set
    var indexRegisterY: SByte = 0u
        private set
    var programCounter: SWord = 0u
        private set
    var stackPointer: SByte = 0u
        private set
    var status: SByte = 0u
        private set

    var carryFlag: Boolean
        get() = status.checkBit(0)
        set(value) = status.setBit(0, value).let { status = it }

    var zeroFlag: Boolean
        get() = status.checkBit(1)
        set(value) = status.setBit(1, value).let { status = it }

    var interruptFlag: Boolean
        get() = status.checkBit(2)
        set(value) = status.setBit(2, value).let { status = it }

    var decimalModeFlag: Boolean
        get() = status.checkBit(3)
        set(value) = status.setBit(3, value).let { status = it }

    var breakFlag: Boolean
        get() = status.checkBit(4)
        set(value) = status.setBit(4, value).let { status = it }

    var overflowFlag: Boolean
        get() = status.checkBit(6)
        set(value) = status.setBit(6, value).let { status = it }

    var negativeFlag: Boolean
        get() = status.checkBit(7)
        set(value) = status.setBit(7, value).let { status = it }

    private val memory: Array<SByte> = Array(0xFFFF) { 0u }

    fun reset() {
        programCounter = readWord(RESET_VECTOR)
        accumulator = 0u
        indexRegisterX = 0u
        indexRegisterY = 0u
        stackPointer = 0xFDu
        status = 0u
        totalCycles = 8u
    }

    private fun readByte(address: SWord): SByte = memory[address.toInt()]

    private fun writeByte(address: SWord, value: SByte) {
        memory[address.toInt()] = value
    }

    private fun readWord(address: SWord): SWord =
        (readByte(address).toSWord() + (readByte(address.inc()).toSWord() shl 8)).toSWord()

    private fun writeWord(address: SWord, value: SWord) {
        memory[address.toInt()] = value.toSByte()
        memory[address.toInt() + 1] = (value shr 8).toSByte()
    }

    // Debug function
    fun getMemory(): Array<SByte> = memory.copyOf()

    // Debug function
    fun clearMemory() {
        memory.fill(0u)
    }

    // Debug function
    fun debugPrint() {
        println(
            "Total Cycles: $totalCycles\n" +
                    "Accumulator: ${accumulator.hex()} | Index Register X: ${indexRegisterX.hex()} | Index Register Y: ${indexRegisterY.hex()} | Program Counter: ${programCounter.hex()} | Stack Pointer: ${stackPointer.hex()}\n" +
                    "Carry: $carryFlag | Zero: $zeroFlag | Interrupt: $interruptFlag | Decimal: $decimalModeFlag | Break: $breakFlag | Overflow: $overflowFlag | Negative: $negativeFlag"
        )
    }
}