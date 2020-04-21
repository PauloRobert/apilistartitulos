package listagemTitulos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Executor {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ListarTodasRegioesTest.class);
		int QuantidadeFalhas = result.getFailureCount();

		for (Failure failure : result.getFailures()) {

			System.out.println("\n" + result.getFailureCount() + " casos de testes falhou " + failure.toString()
					.replace("expected:<201> but was:<200>", "Era esperado o código 200 e deu o 201"));

		}

		System.out.println("\nResultado dos Testes: ");

		if (QuantidadeFalhas > 0) {

			System.out.println("\nForam executados: " + result.getRunCount() + " e " + result.getFailureCount()
					+ " falhou(aram) ");
		} else {
			System.out.println(
					"\nForam executados: " + result.getRunCount() + " Casos de Testes Testes e todos passaram!");

		}

		JFrame parent = new JFrame();

	    JOptionPane.showMessageDialog(parent, "Printing complete");
	}

}
