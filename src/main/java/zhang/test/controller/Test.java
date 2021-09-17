package zhang.test.controller;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @Descripthon: Test
 * @author: MrZhang
 * @date: 2020/12/21 14:55
 */
public class Test {
    /**
     * 创建日志 String.valueOf(Test.class)
     */
    private static final Logger log = Logger.getLogger(Test.class.toString());

    /**
     * 日志测试
     * @param msg 日志消息
     */
    public static void logTest(String msg){
        /* 设置日志输出级别 */
        log.setLevel(Level.INFO);
        log.info("hello world");
        log.finest("finest");
        log.finer("finer");
        log.fine("fine");
        log.config("config");
        log.info("info");
        log.warning("warning");
        log.severe("server");
        log.log(Level.INFO, () -> msg+"log");
        log.info(() -> msg+"log");
    }

    /** String测试
     *
     */
    public static void stringTest() {
        String temp = "Hello";
        int num = 10;

        log.warning(() -> temp + num);
        String temp2 = MessageFormat.format("{0},{1}", num, temp);
        log.warning(temp2);

    }

    /**
     * main方法入口
     * @param args 参数列表
     */
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        BigDecimal bigDecimal = new BigDecimal(0).setScale(2);
        log.info(decimalFormat.format(bigDecimal));
        test(new BigDecimal(""));

    }
    public static void test(BigDecimal s){
        s = s.setScale(2);
    }

    /**
     * 处理sql脚本
     */
    public static void txtScriptDeal(){
        String temp1 = "insert into lcpol (GRPCONTNO,GRPPOLNO,CONTNO,POLNO,PROPOSALNO,PRTNO,CONTTYPE,POLTYPEFLAG,MAINPOLNO,MASTERPOLNO,KINDCODE,RISKCODE,RISKVERSION,MANAGECOM,AGENTCOM,AGENTTYPE,AGENTCODE,AGENTGROUP,AGENTCODE1,SALECHNL,HANDLER,INSUREDNO,INSUREDNAME,INSUREDSEX,INSUREDBIRTHDAY,INSUREDAPPAGE,INSUREDPEOPLES,OCCUPATIONTYPE,APPNTNO,APPNTNAME,CVALIDATE,SIGNCOM,SIGNDATE,SIGNTIME,FIRSTPAYDATE,PAYENDDATE,PAYTODATE,GETSTARTDATE,ENDDATE,ACCIENDDATE,GETYEARFLAG,GETYEAR,PAYENDYEARFLAG,PAYENDYEAR,INSUYEARFLAG,INSUYEAR,ACCIYEARFLAG,ACCIYEAR,GETSTARTTYPE,SPECIFYVALIDATE,PAYMODE,PAYLOCATION,PAYINTV,PAYYEARS,YEARS,MANAGEFEERATE,FLOATRATE,PREMTOAMNT,MULT,STANDPREM,PREM,SUMPREM,AMNT,RISKAMNT,LEAVINGMONEY,ENDORSETIMES,CLAIMTIMES,LIVETIMES,RENEWCOUNT,LASTGETDATE,LASTLOANDATE,LASTREGETDATE,LASTEDORDATE,LASTREVDATE,RNEWFLAG,STOPFLAG,EXPIRYFLAG,AUTOPAYFLAG,INTERESTDIFFLAG,SUBFLAG,BNFFLAG,HEALTHCHECKFLAG,IMPARTFLAG,REINSUREFLAG,AGENTPAYFLAG,AGENTGETFLAG,LIVEGETMODE,DEADGETMODE,BONUSGETMODE,BONUSMAN,DEADFLAG,SMOKEFLAG,REMARK,APPROVEFLAG,APPROVECODE,APPROVEDATE,APPROVETIME,UWFLAG,UWCODE,UWDATE,UWTIME,POLAPPLYDATE,APPFLAG,POLSTATE,STANDBYFLAG1,STANDBYFLAG2,STANDBYFLAG3,OPERATOR,MAKEDATE,MAKETIME,MODIFYDATE,MODIFYTIME,WAITPERIOD,GETFORM,GETBANKCODE,GETBANKACCNO,GETACCNAME,KEEPVALUEOPT,ASCRIPTIONRULECODE,PAYRULECODE,ASCRIPTIONFLAG,PCVALIDATE,COMMONFLAG,COMMONINSURATE,CONTPAYMODE,PERSONPREMRATE,PERSONAMNTRATE) values ('2853012865954','27530118209361','2053012184244566','265301161546264','265301161546264','60201900130014','2','0','265301161546264','','A','191','2006','5301','','01','TK00030449','000000002394','','3','','8156973586','柯远','0',to_date('1988/3/14','yyyy/mm/dd'),'32','1','4','0000569536','元道通信股份有限公司',to_date('2020/9/2','yyyy/mm/dd'),'5301',to_date('2020/9/2','yyyy/mm/dd'),'18:26:05',to_date('','yyyy/mm/dd'),to_date('2021/1/21','yyyy/mm/dd'),to_date('2021/1/21','yyyy/mm/dd'),to_date('2020/9/2','yyyy/mm/dd'),to_date('2021/1/21','yyyy/mm/dd'),'','Y','0','M','5','M','5','','0','','N','','','0','1','12','0','1','','0','3.85','3.85','3.85','100','100','0','0','0','0','0','','',to_date('2021/1/21','yyyy/mm/dd'),to_date('2020/12/9','yyyy/mm/dd'),to_date('2020/9/2','yyyy/mm/dd'),'-2','','','1','','','0','','0','0','','','','','','','','','','0','',to_date('','yyyy/mm/dd'),'','9','gzlongy',to_date('2020/9/2','yyyy/mm/dd'),'14:46:17',to_date('2020/1/22','yyyy/mm/dd'),'4','4','','0','','yuandao',to_date('2020/9/1','yyyy/mm/dd'),'14:01:37',to_date('2020/12/8','yyyy/mm/dd'),'18:06:44','0','','','','','','','','',to_date('2020/9/2','yyyy/mm/dd'),'','0','01','0','0')";

        String[] temp = temp1.split(" values ");
        StringBuilder stringBuilder = new StringBuilder(temp[0]);
        stringBuilder.append(" values (");
        String values = temp[1].replace("to_date(", "").replace("'yyyy/mm/dd'),", "").replace("(", "").replace(")", "");
        log.info(values);
        String[] temp2 = values.split(",");
        for (int i = 0; i < temp2.length; i++) {
            if(i==temp2.length-1){
                stringBuilder.append("?)");
            }else {
                stringBuilder.append("?,");
            }
            System.out.println(temp2[i].replace("'", ""));
        }

        System.out.println(stringBuilder);

    }

    public static void jsonTest(){
        String jsonStr = "{\"id\": 1," +
                " \"age\": 19, \"name\": \"小明\"" +
                "}";
        log.info(jsonStr);
        // 1
        String str1 = "";
        // 2
        String str2 = "";
        // 3
        String str3 = "";
        // 4
        String str4 = "";
        // 5
        String str5 = "";
        // 6
        String str6 = "";

    }

    public static void mapTest(){
        Map<String,Object> codeMap = new HashMap<>(1);
        codeMap.put("1", 1);
        System.out.println(codeMap.get("1"));
    }

    /**
     * 问题描述
     * 现有一个n*n的二维正整数数组nums，每行元素保证递增，每列元素保证递增，求某正整数x是否存在于该二维数组中，需要尽量优化时间和空间复杂度
     * @param nums 二位数组
     * @param target 目标值
     * @return boolean
     */
    public static boolean searchMatrix(int[][] nums, int target){

        if(nums==null || nums.length==0 || nums[0]==null || nums[0].length==0){
            return false;
        }
        int i=0;
        int j=nums[0].length-1;
        while (i < nums.length && j >= 0){
            if(nums[i][j] == target){
                return true;
            }else if(nums[i][j] > target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    public boolean checkSum(int[] nums, int k) {
        if (nums==null||nums.length==0){
            return false;
        }
        int i=0;
        int j=nums.length-1;
        int result=0;
        while (i<=j){
            for (int l = 0; l < nums.length-1; l++) {
                result += nums[l];
                if (result == k){
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public static void CheckSum(double x, double y){
        double i = x / y;
        System.out.println("花费金额：" + i);
        double sum_ = 51.98 + 107.96;
        System.out.println("总金额：" + sum_);



        double rate = i / sum_;
        System.out.println("占比：" + rate);

        double preferential = 67;
        System.out.println("优惠总金额：" + preferential);

        double p = 12 + 1;
        double i_p_ = p * rate;
        System.out.println("自己应付配送费:" + i_p_);

        double myPreferential = preferential * rate;
        System.out.println("自己优惠应占：" + myPreferential);

        double result = i - myPreferential + i_p_;
        System.out.println("我花费金额 - 优惠应占金额：" + result);


    }

    }
