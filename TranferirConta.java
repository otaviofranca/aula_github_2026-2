import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferirConta{

    private final String registro;
    
    public String Transferir(Conta conta1, Conta conta2, double saldo){
        double saldo_atual = conta1.getSaldo();
        if (saldo_atual<saldo){
            registro = "Conta " + conta1 + " com problemas de saldo. Nada foi alterado.";
        }else{
            conta1.DebitarSaldo(saldo);
            conta2.CreditarSaldo(saldo);
            registro = "Conta " + conta1 + " enviou " + saldo + " para a conta " + conta2;
            conta1.RegistrarExtrato(registro)
            conta2.RegistrarExtrato(registro)
            
        }
        return registro;
    }
    // uma conta, para transferir, precisa: metodo Envio_Dinheiro, com checagem se o saldo dela é menor que o que vai ser enviado
    // um metodo para sinalizacao de que em tal conta foi removido ou registrado dinheiro


}