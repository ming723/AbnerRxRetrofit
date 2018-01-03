# AbnerRxRetrofit【作者：AbenrMing,微信公众号：HelloAbner】
### abnerrxretrofit库使用说明：

#### abnerrxretrofit是一个封装了RxJava和Retrofit的一个网络请求库，可自定义携带头部，是否显示加载框,依赖之后，请求网络只需几行代码就能搞定。

#### dialog是一个加载库，包含了像菊花加载，对话框加载等等，我们这里只用到了菊花加载，需要用到更多功能的同学，可以看看。

#### 具体使用：把以上两个库导入自己的项目中，使用到的地方依赖即可。

#### 初始化：
         在Application里或其他地方初始化：
         StyledDialog.init(this);//加载动画初始化
         HttpUtils.getmHttpUtils().create();//abnerrxretrofit库初始化
#### 域名：

    域名是在HttpApi类下，多个域名都可写在这里

#### 具体类说明：
    HttpHelper主要是网络请求类型，包含了四种，1、GET获取String请求 2、POST获取String请求 3、GET获取JavaBean请求 4、POST获取JavaBean请求
    HttpStringCallBackListener是请求返回字符串回调，HttpJavaBeanCallBackListener是请求返回JavaBean回调
#### 代码展示：
    HttpHelper mHttpHelper= new HttpHelper(null,true,this);//有三个参数，第一个是一个传头部的map集合，不传就为null，第二个是否显示加载动画，true就显示，第三个是上下文，用于加载动画的显示
    /**
     * GET获取String请求
     * */
    private void getStringMethod() {
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        mHttpHelper.getString("top250", map, new HttpStringCallBackListener() {
            @Override
            public void success(String message) {
                mTextView.setText(message);
            }

            @Override
            public void failure(String error) {
                mTextView.setText(error);
            }
        });
    }

      /**
       * POST获取String请求
       * */
      private void postStringMethod(){
          Map<String, String> map = new HashMap<>();
          map.put("start", "0");
          map.put("count", "10");
          mHttpHelper.postString("top250", map, new HttpStringCallBackListener() {
              @Override
              public void success(String message) {
                  mTextView.setText(message);
              }

              @Override
              public void failure(String error) {
                  mTextView.setText(error);
              }
          });
      }


     /**
        * GET获取JavaBean请求
        * */
       private void getJavaBeanMethod(){
           Map<String, String> map = new HashMap<>();
           map.put("start", "0");
           map.put("count", "10");
           mHttpHelper.getJavaBean(TestBean.class, "top250", map, new HttpJavaBeanCallBackListener<TestBean>() {
               @Override
               public void success(TestBean testBean) {
                   List<TestBean.Subjects> subjects = testBean.getSubjects();
                   StringBuffer sb=new StringBuffer();
                   for (TestBean.Subjects bean :subjects){
                       sb.append(bean.getTitle()).append(",");
                   }
                   mTextView.setText(sb.toString());
               }

               @Override
               public void failure(String error) {
                   mTextView.setText(error);
               }
           });
       }

       /**
        * POST获取JavaBean请求
        * */
       private void postJavaBeanMethod(){
           Map<String, String> map = new HashMap<>();
           map.put("start", "0");
           map.put("count", "10");
           mHttpHelper.postJavaBean(TestBean.class, "top250", map, new HttpJavaBeanCallBackListener<TestBean>() {
               @Override
               public void success(TestBean testBean) {
                   List<TestBean.Subjects> subjects = testBean.getSubjects();
                   StringBuffer sb=new StringBuffer();
                   for (TestBean.Subjects bean :subjects){
                       sb.append(bean.getTitle()).append(",");
                   }
                   mTextView.setText(sb.toString());
               }

               @Override
               public void failure(String error) {
                   mTextView.setText(error);
               }
           });
       }
