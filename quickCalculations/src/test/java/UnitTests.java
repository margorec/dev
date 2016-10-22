
import com.mycompany.quickCalculations.model.PointWithDistance;
import com.mycompany.quickCalculations.Main;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class UnitTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final BufferedInputStream input;
    private final Main main;

    public UnitTests() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        main = new Main();
        File file = new File(classLoader.getResource(Main.PATH).getFile());
        input = new BufferedInputStream(new FileInputStream(file));
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws IOException {
        input.close();
    }

    @Test
    public void testGetCoordinate() {
        try {
            byte[] coordinate = new byte[4];
            int[] expectedX = new int[]{-715, 761, -194};
            int[] expectedY = new int[]{22165, -23591, 6014};
            int[] resultX = new int[3];
            int[] resultY = new int[3];
            for (int i = 0; i < 3; i++) {
                input.read(coordinate);
                resultX[i] = main.getCoordinate(Arrays.copyOfRange(coordinate, 0, 2));
                resultY[i] = main.getCoordinate(Arrays.copyOfRange(coordinate, 2, 4));
            }

            assertArrayEquals(expectedX, resultX);
            assertArrayEquals(expectedY, resultY);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("No file found to be processed", ex);
        } catch (IOException ex) {
            throw new RuntimeException("Reading error", ex);
        }
    }
    
    @Test
    public void testPointWithDistance() {
        PointWithDistance pwd = new PointWithDistance((short)0, (short)0, (short)-3, (short)-4);
        assertTrue(pwd.getDistance() == 5);
        pwd = new PointWithDistance(Short.MAX_VALUE, Short.MAX_VALUE, Short.MIN_VALUE, Short.MIN_VALUE);
        assertTrue(pwd.getDistance() == 92680.48581012078);
    }
    
}