package util;

import java.util.UUID;

public class CodigoUtil {
    public static String gerarCodigo12Caracteres() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
