import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferirConta{
    
    public String Transferir(Conta conta1, Conta conta2, double saldo){
        String registro;
        double saldo_atual = conta1.getSaldo();
        if (saldo_atual<saldo){
            registro = "Conta " + conta1 + " com problemas de saldo. Nada foi alterado. ";
        }else{
            conta1.debitarSaldo(saldo);
            conta2.creditarSaldo(saldo);
            registro = "Conta " + conta1 + " enviou " + saldo + " para a conta " + conta2;
            conta1.registrarExtrato(registro);
            conta2.registrarExtrato(registro);
            
        }
        return registro;
    }
    // uma conta, para transferir, precisa: metodo Envio_Dinheiro, com checagem se o saldo dela é menor que o que vai ser enviado
    // um metodo para sinalizacao de que em tal conta foi removido ou registrado dinheiro


}