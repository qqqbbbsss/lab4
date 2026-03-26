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

    public List<Double> process(List<Double> nums) {
        List<Integer> ints = toInts(nums);
        List<Integer> proc = variant3(ints);
        return toDoubles(proc);
    }

    public String[] pipeline(String[] input) {
        try {
            List<Double> nums = toNumbers(input);
            List<Double> proc = process(nums);
            return toStrs(proc);
        } catch (NumberFormatException e) {
            System.err.println("ошибка: " + e.getMessage());
            return new String[0];
        }
    }
}