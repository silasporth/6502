import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class StateTest {
    private val state = State()

    @BeforeTest
    fun setup() {
        state.reset()
    }

    @Test
    fun `should be clean reset`() {
        assertEquals(0u, state.accumulator)
        assertEquals(0u, state.indexRegisterX)
        assertEquals(0u, state.indexRegisterY)
        // There should be 0 in the program counter because nothing got loaded into the memory beforehand
        assertEquals(0u, state.programCounter)
        assertEquals(0xFDu, state.stackPointer)
        assertEquals(0u, state.status)
        assertEquals(8u, state.totalCycles)
        state.debugPrint()
    }
}