package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.di;


import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.LoginContract;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.LoginModel;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.LoginPresenter01;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.di
 * Description:模块， @Provides 修饰的方法
 * 在 Component 接口中通过@Component(modules = xxxx.class),封装，统一交给 Component
 * 可以理解为快递包裹容器,以@Provides修饰的方法就是这个包裹包含的物品,然后 Component 接口封装
 * JcChen on 2019/7/13 16:38
 */
@Module
public class LoginModule {
    LoginContract.View view;

    /**
     * 初始化时，传递进来，以便注入给 P 层
     *
     * @param view
     */
    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    /**
     * @return
     * @Provides作用: 该方法在需要提供依赖时被调用，
     * 从而把【预先提供好的对象(注意理解)】当做依赖给标注了@Inject的变量赋值
     * 这种情况是返回module内部保持的变量
     */
    @Provides
    LoginContract.View provideLoginView() {
        return this.view;
    }

    /**
     * 这种含有参数的写法会自动构造参数
     * 但是请注意参数里面的LoginModel 必须有@Inject的构造方法，
     * 或者有@Provides 提供的实例
     *
     * @param loginModel 具体的，必须预先提供(@Inject)
     * @return 接口或者抽象的
     * 注意： 参数和返回值不能是相同的类
     */
    @Provides
    LoginContract.Model provideLoginModel(LoginModel loginModel) {
        return loginModel;
    }

    @Provides
    LoginContract.Presenter provideLoginPresenter(LoginPresenter01 loginPresenter01) {
        return loginPresenter01;
    }


}
