package com.security;

import com.security.engine.VdfEngine;
import com.security.engine.VdfEnginePietrzak;
import com.security.engine.VdfEngineWesolowski;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExamplesTest {
    private static byte[] challenge;
    private static long difficulty;
    private static Random random;

    @BeforeAll
    public static void Setup() {
        challenge = new byte[20];
        difficulty = 100;
        random = new Random();
        random.setSeed(200);
    }

    @Test
    public void test_vdf_Pietrzak() throws NoSuchAlgorithmException {
        random.nextBytes(challenge);
        VdfEngine vdf = new VdfEnginePietrzak(2048);
        byte[] solution = vdf.solve(challenge, difficulty);
        assertEquals("0035e80b6a54563d78c34e365fd17f83fdb8946793b8d3c9fcda2bc257f57e806f72a8faee112a8b07c8cb4afa0d83a7f4e3bc87c4b2a63718a096f4bea1dc9fbb419c58131dc828ea9485ba24e8ef2ca5e68541545b859a106fc3d5da04ed6ede2614d7a7722334531cb4a8b9d6c2d50c68674c5e47b5eea2d89a720250fdf1d400134de5d604874b3c524a9ed4dfa4b892d8065d76f11d1d902561c56bc0960ed2f698caae3ab4c53e14b0cef2018313c6f4325944c44bebb56eb546871979b3242a938c9f3038e2ac2d9026b2c84867df2b2034aea6b2eec10c36b98975473a847cb004fb29a80e66777ed1e0feef4739b25a28defb6e1f7fc3e1e0985b2c6ecd", Hex.toHexString(solution));
        assertTrue(vdf.verify(challenge, difficulty, solution));
        vdf.cleanup();
    }


    @Test
    public void test2_vdf_Pietrzak() throws NoSuchAlgorithmException {
        VdfEngine vdf = new VdfEnginePietrzak(2048);
        byte[] solution = vdf.solve(Hex.decode("c1f72aa5bd1e1d53c723b149259b63f759f40d5ab003b547d5c13d45db9a5da8"), difficulty);
        assertTrue(vdf.verify(Hex.decode("c1f72aa5bd1e1d53c723b149259b63f759f40d5ab003b547d5c13d45db9a5da8"), difficulty, solution));
        vdf.cleanup();
    }

    @Test
    public void test_vdf_Wesolowski() throws NoSuchAlgorithmException {
        random.nextBytes(challenge);
        VdfEngine vdf = new VdfEngineWesolowski(2048);
        byte[] solution = vdf.solve(challenge, difficulty);
        assertEquals("0017d0d2ef462a7b1d432d393deb35b3645c7d5f65e5d738156830c93def0d25ce6b71bc4be502361d0f2d90cabeb4205f1a1d8a8a78f1404eac0ed87d3db7662703e3cae8bb96c9c4654d850032baa52800b2817d9bcabf4f0dd19db12171b4d85ce8d742300dcc617ad3634e6cc89530dbb747b7a143b7cbf787dd7bb9d8c4c8fff29b951f3756ef8655dcc8a08286cb498ee7389ae07203329bd45332f768e62b0548633241dd85202ddb40233259fa22f98ff14dc0e723135926c90cb3e5a85e0ae6e7fcd1e256d9f5ece441a43bc0285a128e374df12a66265de3a86dc8f98675730abd7c7bc029eb910a008fd30a33f1ec25dd1c6353444cf5eb70749105bf000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001", Hex.toHexString(solution));
        assertTrue(vdf.verify(challenge, difficulty, solution));
        vdf.cleanup();
    }


    @Test
    public void test2_vdf_Wesolowski() throws NoSuchAlgorithmException {
        VdfEngine vdf = new VdfEngineWesolowski(2048);
        byte[] solution = vdf.solve(Hex.decode("c1f72aa5bd1e1d53c723b149259b63f759f40d5ab003b547d5c13d45db9a5da8"), difficulty);
        assertTrue(vdf.verify(Hex.decode("c1f72aa5bd1e1d53c723b149259b63f759f40d5ab003b547d5c13d45db9a5da8"), difficulty, solution));
        vdf.cleanup();
    }

    @Test
    public void streess_test_vdf() throws InterruptedException {
        int count = 7;
        while (count > 0) {
            random.nextBytes(challenge);
            VdfEngine vdf = new VdfEnginePietrzak(2048);
            byte[] solution = vdf.solve(challenge, difficulty);
            assertTrue(vdf.verify(challenge, difficulty, solution));
            count--;
            System.out.print(count);
            vdf.cleanup();
        }
    }
}
