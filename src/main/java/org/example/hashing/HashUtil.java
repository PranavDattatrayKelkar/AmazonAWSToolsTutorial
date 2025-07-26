package org.example.hashing;

import java.nio.charset.StandardCharsets;

public class HashUtil {
        /**
         * This API is used to return 64-bit MurmurHash3 for a String key
         *
         * @param key input key to create hash for
         * @return long hashed key
         */
        public static long hashLong(String key) {
            byte[] data = key.getBytes(StandardCharsets.UTF_8);
            return murmurHash3_x64_64(data, 0);
        }

        private static long murmurHash3_x64_64(byte[] data, int seed) {
            final int length = data.length;
            final int nblocks = length >>> 3;

            long h1 = seed & 0xFFFFFFFFL;
            long c1 = 0x87c37b91114253d5L;
            long c2 = 0x4cf5ad432745937fL;

            // Body
            for (int i = 0; i < nblocks; i++) {
                int i8 = i << 3;
                long k1 = ((long) data[i8] & 0xff) |
                        (((long) data[i8 + 1] & 0xff) << 8) |
                        (((long) data[i8 + 2] & 0xff) << 16) |
                        (((long) data[i8 + 3] & 0xff) << 24) |
                        (((long) data[i8 + 4] & 0xff) << 32) |
                        (((long) data[i8 + 5] & 0xff) << 40) |
                        (((long) data[i8 + 6] & 0xff) << 48) |
                        (((long) data[i8 + 7] & 0xff) << 56);

                k1 *= c1;
                k1 = Long.rotateLeft(k1, 31);
                k1 *= c2;

                h1 ^= k1;
                h1 = Long.rotateLeft(h1, 27) * 5 + 0x52dce729;
            }

            // Tail
            long k1 = 0;
            int tailStart = nblocks << 3;
            switch (length & 7) {
                case 7: k1 ^= ((long) data[tailStart + 6] & 0xff) << 48;
                case 6: k1 ^= ((long) data[tailStart + 5] & 0xff) << 40;
                case 5: k1 ^= ((long) data[tailStart + 4] & 0xff) << 32;
                case 4: k1 ^= ((long) data[tailStart + 3] & 0xff) << 24;
                case 3: k1 ^= ((long) data[tailStart + 2] & 0xff) << 16;
                case 2: k1 ^= ((long) data[tailStart + 1] & 0xff) << 8;
                case 1:
                    k1 ^= ((long) data[tailStart] & 0xff);
                    k1 *= c1;
                    k1 = Long.rotateLeft(k1, 31);
                    k1 *= c2;
                    h1 ^= k1;
            }

            // Finalization
            h1 ^= length;
            h1 = fmix64(h1);

            return h1;
        }

        private static long fmix64(long k) {
            k ^= k >>> 33;
            k *= 0xff51afd7ed558ccdL;
            k ^= k >>> 33;
            k *= 0xc4ceb9fe1a85ec53L;
            k ^= k >>> 33;
            return k;
        }
}
