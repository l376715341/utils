package com.example.demo;

class Solution {
    public boolean isUnique(String astr) {
        int a=0,temp=0;
        char[] ch=astr.toCharArray();
        for(int i=0;i<ch.length;i++){
            temp=ch[i]-'a';
            if((a&1<<temp)!=0) return false;
            a|=1<<temp;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().isUnique("abcc"));
    }
}
