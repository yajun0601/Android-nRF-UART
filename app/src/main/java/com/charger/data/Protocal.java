package com.charger.data;

/**
 * Created by zhengyajun on 17/2/16.
 */
public class Protocal {

    public final static int  L1_HEADER_MAGIC_POS = 0, //(0)
        L1_HEADER_LENGTH_POS = 1,
        L1_HEADER_CMD_POS = 2, // (2)
        L1_HEADER_CRC16_HIGH_BYTE_POS = 3, // (3)
        L1_HEADER_CRC16_LOW_BYTE_POS = 4, // (4)
        L1_HEADER_DATA_START_BYTE_POS = 5; // (5)
    public final static int L1_HEADER_SIZE = 4;
    public final byte FIRST_CMD_ID = 0,  // 0
    BATTERY_CMD_ID = 1,  // 1
    DC_CHARGER_CMD_ID = 2,
    DCDC_CMD_ID = 3,
    AC_CHARGER_CMD_ID = 4,
    DCAC_CMD_ID = 5,
    FAN_CMD_ID = 6,
    AC_CHARGE_START_CMD_ID = 7,
    DC_CHARGE_START_CMD_ID = 8,
    LAST_CMD_ID = 9;

    private int status;
    private int payload_lenght;
    public static final int  MAGIC_WORD = 0xAB;
    public static Battery battery = null;
    public static DCDC_Gun dcCharger = null;
//    char chars[];
    public Protocal(byte str[]){  /*factory ?*/
        // 数据不全的情况未考虑
        if (!resolve_L1_header(str))
            return;
        payload_lenght = str[L1_HEADER_LENGTH_POS]&0x3f;
        if (resolve_L1_header(str)){

            int vol, curr, res_cap, temp, stat, charged_capacity, charged_time;
            switch (str[L1_HEADER_CMD_POS]){
                case BATTERY_CMD_ID:

                    vol = str[L1_HEADER_DATA_START_BYTE_POS] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 1];
                    curr = str[L1_HEADER_DATA_START_BYTE_POS + 2] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 3];
                    res_cap = str[L1_HEADER_DATA_START_BYTE_POS + 4] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 5];
                    temp    = str[L1_HEADER_DATA_START_BYTE_POS + 6];
                    stat    = str[L1_HEADER_DATA_START_BYTE_POS + 7];
                    battery = new Battery(vol, curr, res_cap, temp, stat);
                    break;
                case DC_CHARGER_CMD_ID:
                    vol = str[L1_HEADER_DATA_START_BYTE_POS] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 1];
                    curr = str[L1_HEADER_DATA_START_BYTE_POS + 2] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 3];
                    charged_capacity = str[L1_HEADER_DATA_START_BYTE_POS + 4] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 5];
                    charged_time = str[L1_HEADER_DATA_START_BYTE_POS + 6] << 8 | str[L1_HEADER_DATA_START_BYTE_POS + 7]
                    temp    = str[L1_HEADER_DATA_START_BYTE_POS + 8];
                    stat    = str[L1_HEADER_DATA_START_BYTE_POS + 9];

                    dcCharger = new DCDC_Gun(vol, curr, charged_capacity, charged_time, temp, stat);
                    break;
            }
            resolve_L1(str);
        }


    }
    boolean resolve_L1(byte str[]){

        return true;
    }
    boolean resolve_L1_header(byte str[]){
        if (str[L1_HEADER_MAGIC_POS] != MAGIC_WORD)
            return false;
        if (!isCMDValid(str[L1_HEADER_CMD_POS]))
            return false;
        int crc16 = str[L1_HEADER_CRC16_HIGH_BYTE_POS] << 8 | str[L1_HEADER_CRC16_LOW_BYTE_POS];
        if (CRC16.calcCrc16(str,L1_HEADER_DATA_START_BYTE_POS,str[L1_HEADER_LENGTH_POS]&0x3f) != crc16) {
            return false;
        }

        return true;
    }
    boolean isCMDValid(byte cmd)
    {
        if (cmd > FIRST_CMD_ID && cmd < LAST_CMD_ID)
            return true;
        else
            return false;
    }

}
