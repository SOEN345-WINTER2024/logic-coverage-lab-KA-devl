import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CheckItTest {

    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream testOutputContent = new ByteArrayOutputStream();

    public void setUpOutput() {
        System.setOut(new PrintStream(testOutputContent));
    }

    public void restoreSystemOutput() {
        System.setOut(originalSystemOut);
        testOutputContent.reset();
    }

    // CLAUSE COVERAGE
    @Test
    public void testAllTrueClauses_ShouldOutputTrue() {
        setUpOutput();
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    @Test
    public void testAllFalseClauses_ShouldOutputFalse() {
        setUpOutput();
        CheckIt.checkIt(false, false, false);
        assertEquals("P isn't true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    // PREDICATE
    @Test
    public void testPredicateTrue_ShouldOutputTrue() {
        setUpOutput();
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    @Test
    public void testPredicateFalse_ShouldOutputFalse() {
        setUpOutput();
        CheckIt.checkIt(false, true, false);
        assertEquals("P isn't true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    // CACC
    @Test
    public void testCACCTrue_ShouldOutputTrue() {
        setUpOutput();
        CheckIt.checkIt(true, true, false);
        assertEquals("P is true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    @Test
    public void testCACCFailure_ShouldOutputFalse() {
        setUpOutput();
        CheckIt.checkIt(false, true, false);
        assertEquals("P isn't true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    // RACC
    @Test
    public void testRACCTrue_ShouldOutputTrue() {
        setUpOutput();
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }

    @Test
    public void testRACCFailure_ShouldOutputFalse() {
        setUpOutput();
        CheckIt.checkIt(false, false, true);
        assertEquals("P isn't true", testOutputContent.toString().trim());
        restoreSystemOutput();
    }
}
