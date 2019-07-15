package cashregister;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import sun.plugin.PluginURLJarFileCallBack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class CashRegisterTest {
    static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeAll
    static public void setup() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }





    @Test
    public void should_print_the_real_purchase_when_call_process() {
        Item item = new Item("coco", 15);
        Item[] items = {item};
        Purchase purchase = new Purchase(items);
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        cashRegister.process(purchase);

        Assertions.assertEquals("coco\t15.0\n",byteArrayOutputStream.toString());

    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        Purchase purchase = mock(Purchase.class);
        CashRegister cashRegister = new CashRegister(new Printer());
        when(purchase.asString()).thenReturn("mock purchase");

        cashRegister.process(purchase);

        Assertions.assertEquals("mock purchase",byteArrayOutputStream.toString());

    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        Purchase purchase = mock(Purchase.class);
        CashRegister cashRegister = new CashRegister(new Printer());
        when(purchase.asString()).thenReturn("mock purchase");

        cashRegister.process(purchase);

        verify(purchase).asString();


    }

}
