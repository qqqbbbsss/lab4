package core;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public List<Double> processData(List<Double> in) {
        if (in == null || in.isEmpty()) {
            return new ArrayList<>();
        }

        List<Double> out = new ArrayList<>();
        out.add(in.get(0));

        for (int i = 1; i < in.size(); i++) {
            if (!in.get(i).equals(in.get(i - 1))) {
                out.add(in.get(i));
            }
        }
        return out;
    }

    public String[] processPipeline(String[] in) {
        try {
            List<Double> nums = toNumbers(in);
            List<Double> res = processData(nums);
            return toStrs(res);
        } catch (NumberFormatException e) {
            System.err.println("ошибка: " + e.getMessage());
            return new String[0];
        }
    }

    private List<Double> toNumbers(String[] data) throws NumberFormatException {
        List<Double> nums = new ArrayList<>();
        for (String s : data) {
            s = s.trim();
            if (!s.isEmpty()) {
                nums.add(Double.parseDouble(s));
            }
        }
        return nums;
    }

    private String[] toStrs(List<Double> nums) {
        String[] res = new String[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            double v = nums.get(i);
            if (v == (long) v) {
                res[i] = String.valueOf((long) v);
            } else {
                res[i] = String.valueOf(v);
            }
        }
        return res;
    }
}