package com.example.justlhyutils.hashCode;

import com.alibaba.fastjson.JSON;
import com.example.justlhyutils.model.Customer;
import com.example.justlhyutils.model.ReturnShelfDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/11/9 18:49
 */
public class testHashCodeAndEquals {
    public static void main(String[] args) {

        List<Customer> list1 = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setName("222");
        list1.add(customer1);
        List<Customer> list2 = list1;
        Customer customer2 = new Customer();
        customer1.setName("444");
        customer2.setName("332");
        list1.add(customer2);
        System.out.println(JSON.toJSONString(list2));
        System.out.println(JSON.toJSONString(list1));

        System.out.println(getYearBatch("0304"));

        List<ReturnShelfDto> rstList = Lists.newArrayList();
        rstList=null;
        for (ReturnShelfDto returnShelfDto : rstList) {

        }

        String a2  = "{\"returnShelfEnable\":true,\"returnShelfScanCode\":true}";
        ReturnShelfDto returnShelfDto = JSON.parseObject(a2, ReturnShelfDto.class);
        System.out.println(JSON.toJSONString(returnShelfDto));
        String s = "1-2-3";
        System.out.println(s.startsWith("1-2-3-4"));
        String str  = "{\"id\":1000376633229,\"coverDifferFlag\":\"0\",\"insteadInvent\":1}";
        MstInfo mstInfo = JSON.parseObject(str, MstInfo.class);
        System.out.println(Objects.equals(mstInfo.getInsteadInvent(),"1"));
        String a = "yh-delivery-app,ofs-thirdparty-service,ofs-delivery-rest,ofs-thirdparty-transport,ofs-logistics-facade,yh-delivery-app,ofs-vip-job-scheduler,ofs-delivery-rest,ofs-delivery-rest,ofs-logistics-makeup,yh-delivery-app,yhwms_flutter,yhwms_flutter,yh-super-partner-H5,shop-rest-api,partner-rest-api,partner-center,partner-hub,partner-print-center,yh-delivery-app,ofs-delivery-rest,ofs-work-center,ofs-rider-center,ofs-billing-center,ofs-delivery-rest,partner-rest-api,ofs-thirdparty-service,yh-delivery-app,yhwms_flutter,yh-super-partner-H5,yh-delivery-app,yhwms_flutter,yh-super-partner-H5,partner-print-center,ofs-delivery-rest,ofs-logistics-makeup,ofs-delivery-center,ofs-logistics-facade,yh-delivery-app,yh-delivery-app,ofs-delivery-center,ofs-delivery-makeup,ofs-delivery-rest,ofs-logistics-facade,ofs-logistics-makeup,partner-rest-api,partner-center,ofs-vip-job-scheduler,manager-web-v2,ofs-management-hub,picklist-center,warehouse-outbound-task-makeup,yh-delivery-app,ofs-shop-center,ofs-thirdparty-service,ofs-vip-job-scheduler,ofs-logistics-makeup,partner-center,partner-rest-api,picklist-center,warehouse-outbound-makeup,ofs-vip-job-scheduler,warehouse-outbound-center,partner-print-center,picklist-center,ofs-work-center,partner-center,partner-center,picklist-center,partner-center,partner-rest-api,shop-rest-api,ofs-logistics-makeup,ofs-logistics-facade,ofs-delivery-rest,ofs-delivery-makeup,ofs-billing-center,ofs-thirdparty-service,yh-delivery-app,picklist-center,partner-center,ofs-fulfil-makeup,warehouse-outbound-makeup,warehouse-outbound-task-makeup,partner-rest-api,ofs-thirdparty-transport,ofs-open-api,manager-web-v2";
        String[] split = a.split(",");
        HashSet<String> strings = Sets.newHashSet(split);
        System.out.println(strings.size());
    }

    private static int sortBySkuCode(List<String> finalSortedCodeList, Customer o1, Customer o2) {
        int cr = 0;
        //判断是否有配置分类排序
        if (CollectionUtils.isEmpty(finalSortedCodeList)) {
            return cr;
        }
        String categoryCode1 = o1.getName();
        String categoryCode2 = o2.getName();
        String midCode1 = "";
        String midCode2 = "";
        String bigCode1 = "";
        String bigCode2 = "";
        if(categoryCode1.length() > 6) {
            midCode1 = categoryCode1.substring(0,6);
        }
        if(categoryCode2.length() > 6) {
            midCode2 = categoryCode2.substring(0,6);
        }
        if(categoryCode1.length() > 6) {
            bigCode1 = categoryCode1.substring(0,4);
        }
        if(categoryCode2.length() > 6) {
            bigCode2 = categoryCode2.substring(0,4);
        }
        //大类排序
        Integer big1Index = finalSortedCodeList.indexOf(bigCode1);
        big1Index = big1Index == -1 ? 9999 :big1Index;
        int big2Index = finalSortedCodeList.indexOf(bigCode2);
        big2Index = big2Index == -1 ? 9999 :big2Index;
        //中类顺序
        Integer mid1Index = finalSortedCodeList.indexOf(midCode1);
        mid1Index = mid1Index == -1 ? 9999 :mid1Index;
        int mid2Index = finalSortedCodeList.indexOf(midCode2);
        mid2Index = mid2Index == -1 ? 9999 :mid2Index;
        //小类顺序
        Integer small1Index = finalSortedCodeList.indexOf(categoryCode1);
        small1Index = small1Index == -1 ? 999999 : small1Index;
        int small2Index = finalSortedCodeList.indexOf(categoryCode2);
        small2Index = small2Index == -1 ? 999999 : small2Index;
        //先排大类再排中类再排小类
        cr = big1Index.compareTo(big2Index);
        if(cr != 0) {
            return cr;
        }
        cr = mid1Index.compareTo(mid2Index);
        if(cr != 0) {
            return cr;
        }
        cr = small1Index.compareTo(small2Index);
        if(cr != 0) {
            return cr;
        }
        return cr;
    }

    private static String getYearBatch(String batch) {
        if (StringUtils.isBlank(batch)) {
            return null;
        }
        int curMonth = LocalDate.now().getMonthValue();
        int curDay = LocalDate.now().getDayOfMonth();
        if (batch.length() == 4) {
            int scanMonth = Integer.parseInt(batch.substring(0, 2));
            int scanDay = Integer.parseInt(batch.substring(2, 4));
            if (scanMonth - curMonth > 2 || (scanMonth - curMonth == 2 && scanDay > curDay)) {
                return (LocalDate.now().getYear() - 1) + batch;
            }
        }
        return LocalDate.now().getYear() + batch;
    }


}
