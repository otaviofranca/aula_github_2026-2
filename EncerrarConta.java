
import java.util.ArrayList;


public class EncerrarConta{
    
    private final ArrayList<Conta> contasAtivas;
    private final ArrayList<Conta> contasInativas;


    public EncerrarConta(ArrayList<Conta> contasAtivas, ArrayList<Conta> contasInativas){
        this.contasAtivas = contasAtivas;
        this.contasInativas = contasInativas; // lembrando que o main vai passar uma referencia na memoria para o objeto do array list
    }
    public void Encerrar(Conta conta, int id) throws ContaJaInativa{
            if (contasInativas.contains(conta)){
                throw new ContaJaInativa("Nao é possivel encerrar a conta: Conta ja inativa.");
        }else{
        
        if (contasAtivas.contains(conta)){
            double saldo = conta.getSaldo();

        if (saldo==0){
            contasAtivas.remove(conta);
            contasInativas.add(conta);
            System.out.println("Foi colocada como Inativa a conta seguinte: " + id);
        }else{
            System.out.println("A conta possui saldo positivo, portanto nao pode se tornar inativa.");
        }
    }
        }
        
    }

}