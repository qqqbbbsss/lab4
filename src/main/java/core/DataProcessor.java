package core;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public List<Double> toNumbers(String[] data) throws NumberFormatException {
        List<Double> nums = new ArrayList<>();
        for (String s : data) {
            s = s.trim();
            if (!s.isEmpty()) {
                nums.add(Double.parseDouble(s));
            }
        }
        return nums;
    }

    public String[] toStrs(List<Double> nums) {
        String[] res = new String[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            double val = nums.get(i);
            if (val == (long) val) {
                res[i] = String.valueOf((long) val);
            } else {
                res[i] = String.valueOf(val);
            }
        }
        return res;
    }

    public List<Integer> toInts(List<Double> nums) {
        List<Integer> ints = new ArrayList<>();
        for (Double d : nums) {
            ints.add(d.intValue());
        }
        return ints;
    }

    public List<Double> toDoubles(List<Integer> ints) {
        List<Double> dubs = new ArrayList<>();
        for (Integer i : ints) {
            dubs.add(i.doubleValue());
        }
        return dubs;
    }

    public List<Integer> variant3(List<Integer> lst) {
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        res.add(lst.get(0));
        for (int i = 1; i < lst.size(); i++) {
            if (!lst.get(i).equals(lst.get(i - 1))) {
                res.add(lst.get(i));
            }
        }
        return res;
    }

public List<Integer> variant8(List <Integer> lst){
    if (lst==null||lst.isEmpty()){
        return new ArrayList<>();
    }
    List<Integer> result = new ArrayList<>(lst);
    List<Integer> positive = new ArrayList<>();

    for (Integer num:result){
        if (num>0){
            positive.add(num);
        }
    }
    positive.sort(Integer::compareTo);
    int posIndex=0;
    for(int i=0;i<result.size();i++){
        if (result.get(i)>0){
            result.set(i, positive.get(posIndex++));
        }
    }
    return result;
    }


    public List<Integer> variant9(List<Integer> lst) {
        if (lst == null || lst.size() < 2) {
            return new ArrayList<>(lst);
        }
        int firstMaxIndex = 0;
        int maxValue = lst.get(0);
        for (int i = 1; i < lst.size(); i++) {
            if (lst.get(i) > maxValue) {
                maxValue = lst.get(i);
                firstMaxIndex = i;
            }
        }
        int lastMinIndex = lst.size() - 1;
        int minValue = lst.get(lst.size() - 1);
        for (int i = lst.size() - 2; i >= 0; i--) {
            if (lst.get(i) < minValue) {
                minValue = lst.get(i);
                lastMinIndex = i;
            }
        }

        if (firstMaxIndex >= lastMinIndex - 1) {
            return new ArrayList<>(lst);
        }

        List<Integer> result = new ArrayList<>(lst);
        reverseSublist(result, firstMaxIndex + 1, lastMinIndex - 1);
        return result;
    }

    private void reverseSublist(List<Integer> list, int start, int end) {
        while (start < end) {
            int temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }

    public String[] pipeline(String[] input, int variant) {
        try {
            if (input == null || input.length == 0) {
                System.err.println("ошибка");
                return new String[0];
            }

            List<Double> nums = toNumbers(input);
            List<Integer> ints = toInts(nums);
            List<Integer> proc = null;

            switch (variant) {
                case 1:
                    proc = variant3(ints);
                    break;
                case 2:
                    proc = variant8(ints);
                    break;
                case 3:
                    proc = variant9(ints);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "ошибка");
            }

            if (proc == null) {
                System.err.println("ошибка");
                return new String[0];
            }

            return toStrs(toDoubles(proc));

        } catch (NumberFormatException e) {
            System.err.println("ошибка" + e.getMessage());
            return new String[0];
        } catch (IllegalArgumentException e) {
            System.err.println("ошибка" + e.getMessage());
            return new String[0];
        } catch (Exception e) {
            System.err.println("ошибка " + e.getMessage());
            e.printStackTrace();
            return new String[0];
        }
    }}