class Solution {
    public int evalRPN(String[] tokens) {
        /*
        String[] 是不是 List？	❌ 不是。它是固定長度的陣列
        為什麼要 Arrays.asList()？	✅ 把陣列轉成 List 來使用集合功能
        為什麼還要包 new ArrayList<>(...)？	✅ 才能 add/remove，否則是固定長度的 pseudo-List
        */
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokens));
        
        // 遍歷每一個元素，若該元素為"+-*/"就把前兩個元素用該運算符號做運算
        while (tokenList.size() > 1){
            for (int i = 0; i < tokenList.size(); i++){
                String token = tokenList.get(i);
                
                if ("+-*/".contains(token)){
                    // Integer.parseInt(str) 把str轉int
                    int a = Integer.parseInt(tokenList.get(i - 2));
                    int b = Integer.parseInt(tokenList.get(i - 1));
                    int result = 0;

                    // == 比的是記憶體位置（reference）; .equals() 比的是內容（value）
                    // == 用在字串通常會錯誤！！！
                    if (token.equals("+")){
                        result = a + b;
                    } else if (token.equals("-")){
                        result = a - b;
                    } else if (token.equals("*")){
                        result = a * b;
                    } else if (token.equals("/")){
                        result = a / b;
                    }

                    // 把i - 2的元素值設為result；i、i - 1均remove
                    // String.valueOf(int) 把int轉為str
                    tokenList.set(i - 2, String.valueOf(result));
                    tokenList.remove(i);
                    tokenList.remove(i - 1);
                    break;  // 🔴 必須加這行，避免修改 list 後繼續用原來的 index
                }
            }
        }
        // 最後除了結果值，其他值都會被刪除
        return Integer.parseInt(tokenList.get(0));
    }
}
