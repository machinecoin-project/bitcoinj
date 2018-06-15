/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 178;
        addressHeader = 50;
        p2shHeader = 20;
        p2wpkhHeader = 6;
        p2wshHeader = 10;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader, p2wpkhHeader, p2wshHeader };
        port = 40333;
        packetMagic = 0xdbb6c0fbL;
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"
        bip49HeaderPub = 0x049D7CB2; //The 4 byte header that serializes in base58 to "ypub".
        bip49HeaderPriv = 0x049D7878; //The 4 byte header that serializes in base58 to "yprv"
        bip84HeaderPub = 0x04B24746; //The 4 byte header that serializes in base58 to "zpub".
        bip84HeaderPriv = 0x04B2430C; //The 4 byte header that serializes in base58 to "zprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1389040865);
        genesisBlock.setNonce(3716037);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 350000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("6a1f879bcea5471cbfdee1fd0cb2ddcc4fed569a500e352d41de967703e83172"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(23021, Sha256Hash.wrap("0268f4e816aac0874c911c83e263353289854c94a21cf97675652419893e7d8f"));
        checkpoints.put(53600, Sha256Hash.wrap("327ec569aa2439c16542ed9402884f5ce691d08f49168d672f19f817ace7a06b"));
        checkpoints.put(112715, Sha256Hash.wrap("4fba0d1f891a35a7e0b7370d13b777e75fd2826423ef777ccca2f63d6acc70c5"));
        checkpoints.put(130938, Sha256Hash.wrap("3fc5ccce46b45775ea3cb9f0d10169227bbd019518ebb90e5d6b8a770bf85d1d"));
        checkpoints.put(148401, Sha256Hash.wrap("0c6b00515da19b9a95571e8cc61447442f6ade0f2e10e3b9ee6df133a76a809f"));

        dnsSeeds = new String[] {
                "dnsseed1.machinecoin.io",
                "dnsseed2.machinecoin.io",
                "dnsseed3.machinecoin.io"
        };
        httpSeeds = new HttpDiscovery.Details[] {
        };

        addrSeeds = new int[] {
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
