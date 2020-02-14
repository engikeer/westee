package com.mfun.proxy;

import org.springframework.stereotype.Service;

@Service
public class MathCalculator implements Calculator {

    @Override
    public int div(int i, int j) {
        System.out.println("实际执行除法");
        return i / j;
    }

}
