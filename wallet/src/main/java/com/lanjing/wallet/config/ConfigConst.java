package com.lanjing.wallet.config;

public class ConfigConst {
    //首次理财奖励EXC最大界限
    public static final int REWARD_LIMIT = 100;
    //首次理财奖励EXC最大额
    public static final double MAXIMUM_REWARD = 12000;
    //首次理财奖励EXC每日释放比
    public static final double RELEASE = 1;
    //DZCC id
    public static final int DZCC_COIN = 5;
    public static final int ETH_COIN = 1;
    //ET id
    public static final int ET_COIN = 4;
    public static final int EXC_COIN = 2;
    
    //平台主地址
    public static final String PLATFORM_MAIN_ADDRESS = "0xb8a6a5191199b7d07f9101e2d1b2e9ed0b74407c";    
    
    //平台代币地址 AGC
    public static final String PLATFORM_ADDRESS = "0x21d2d38d4fb1cef64b96cc77c6fa6830fbd380ae";

    public static final String PLATFORM_ADDRESS1 = "0xdac17f958d2ee523a2206206994597c13d831ec7";//usdt合约地址

    //收币地址
    public static final String Receive_ADDRESS = "0x5e371B977f543A5F34F0FE12155d60d2b90839Ec";
    public static final String BTCReceive_ADDRESS = "1MgaDywRspvyaimUTnLXTWnDkKsUSeSd9a";

    //转出地址
    /*public static final String Turn_ADDRESS = "0xaf2364fe6d1c422ac4c63de26c9bc65b24397c0d";
    public static final String Turn_PWD = "afdgfashgfdhg";*/


    /*public static final String Turn_ADDRESS = "0x88d1518da81d34dde9a193ef94f736c2e3328837";
    public static final String Turn_PWD = "9962abcABC156456";*/


    public static final String Turn_ADDRESS = "0x65A3772F0A22dB5BB691b2E223aB2c5444dDe88a";
    public static final String Turn_PWD = "SADAFAGAnvxvf236546$#%";


    public static final String BTCTurn_ADDRESS = "3Gz1YUXRMUk8Lc3vPjWGXdHa6ysxjY93PZ";

    //节点地址
    public static final String NODE_ADDRESS = "http://154.92.14.117:8085";

    public static final String BTCNODE_ADDRESS = "http://47.56.106.45:9001";
    //节点响应成功
    public static final String M00000 = "M00000";
    //单次参与理财：最小0.5ETH
    public static final double ETH_MIN = 0.5;
    //单次参与理财：最大100ETH
    public static final double ETH_MAX = 100;
    //推广EXC奖励每日释放比
    public static final double SPREAD_RELEASE = 1;
    //团队EXC奖励每日释放比
    public static final double TEAM_RELEASE = 1;
    //EXC奖励
    public static final String BONUS = "bonus";
    //EXC释放
    public static final String TOTAL = "total";
    //图片访问路径
    public static final String IMG_URL = "imgurl";
    //图片上传路径
    public static final String IMG_BATH = "imgbath";
    //banner图
    public static final String BANNER = "banner%d";
    //ETH币种名称
    public static final String ETH_NAME = "ETH";
    //USDT币种名称
    public static final String USDT_NAME = "USDT";
    //默认汇率
    public static final double DEFAULT_RATE = 2197.84;
    //直推分红比率
    public static final double PUSH_REWARD = 50;
    //直推分红自身锁仓量
    public static final double LOCK_VOLUME = 10000;
    //小节点门槛
    //自身持币量
    public static final int HELD_CURRENCY = 10000;
    //直推持币锁仓用户数
    public static final int PUSH_USER = 10;
    //小节点团队分红比率
    public static final double SMALL_RATE = 5;
    //中节点团队分红比率
    public static final double MEDIUM_RATE = 10;
    //大节点团队分红比率
    public static final double BIG_RATE = 15;
    //平级或更高级别的节点，则可以获得该节点的动态奖励（直推+团队分红）比率
    public static final double LEVEL_RATE = 10;
    //购物释放推广奖励比率、直推
    public static final double DIRECT_PUSH = 30;
    //购物释放推广奖励比率、间推
    public static final double PUSH = 20;
    //最小提币金额1000DZCC
    public static final double MIN_COIN = 1000;
    //交易密码错误次数key
    public static final String KEY_PASS = "user:check:pass:";
    //是否释放key
    public static final String FREED = "freed";
    //BTC人民币价格ID
    public static final Integer BTC_PRICE_RMB_ID = 8;
    //BTC ID
    public static final Integer BTC_ID = 3;
    //YYB ID
    public static final Integer YYB_ID = 2;
    //积分 ID
    public static final Integer INTEGRAL_ID = 4;
    //金色财经key
    public static final String GOLDEN_FINANCE = "golden:finance";
}
