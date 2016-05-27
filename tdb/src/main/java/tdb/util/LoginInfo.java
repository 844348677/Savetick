package tdb.util;

import org.bridj.Pointer;
import tdbapi.OPEN_SETTINGS;
import tdbapi.TDBDefine_ResLogin;

/**
 * Created by liuh on 2016/5/9.
 */
public class LoginInfo {
    public static Pointer<OPEN_SETTINGS> getOpsPointer(String ip, String port, String user, String password){

        Pointer<OPEN_SETTINGS> opsPointer = Pointer.allocate(OPEN_SETTINGS.class); //创建OPEN_SETTINGS类型的指针，方法对不对我也不知道，反正能用
        OPEN_SETTINGS ops =opsPointer.get(); //拿到OPEN_SETTING对象

        Pointer<Byte> ipPointer = ops.szIP(); //拿到szIP的指针对象，再设置ip
        byte[] ipBtyes = ip.getBytes();
        ipPointer.setArray(ipBtyes);

        Pointer<Byte> portPointer = ops.szPort();
        byte[] portBytes = port.getBytes();
        portPointer.setArray(portBytes);

        Pointer<Byte> userPointer = ops.szUser();
        byte[] userBytes = user.getBytes();
        userPointer.setArray(userBytes);

        Pointer<Byte> passwordPointer = ops.szPassword();
        byte[] passwordBytes = password.getBytes();
        passwordPointer.setArray(passwordBytes);

        System.out.println(opsPointer);
        System.out.println(opsPointer.get().toString());
        System.out.println(opsPointer.get().szIP().toString());
        System.out.println(opsPointer.get().szIP().get(1).toString());

        return opsPointer;
    }

    public static String[] getMarkets(Pointer<TDBDefine_ResLogin> loginResPointer){
        TDBDefine_ResLogin loginRes =  loginResPointer.get();
        int nMarkets = loginRes.nMarkets(); //支持市场个数
        System.out.println("支持市场个数: "+nMarkets);
        String[] result = new String[nMarkets];  //结果 支持市场的String[]数组
        byte[] szMarketByts = loginRes.szMarket().getBytes();
        for(int i=0;i<result.length;i++){
            byte[] tmp = new byte[24];
            for(int j=0;j<tmp.length;j++)
                tmp[j]=szMarketByts[i*24 + j];
            result[i] = new String(tmp).trim();
            System.out.println(result[i]);
        }
        return result;
    }
}
