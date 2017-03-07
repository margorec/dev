import org.junit.Test;
import org.mockito.Mock;
import tree.Node;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class NodeTest {

    @Mock Node<Integer> parent, left, right;

    @Test
    public void testNodeValue() {
        Node<Integer> testNode = new Node<>(parent, left, right, Integer.MAX_VALUE);
        assertThat(testNode.getValue() instanceof Integer, is(true));
    }

}
