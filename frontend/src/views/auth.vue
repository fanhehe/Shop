<template>
    <div class = "container">
        <header class = "header auth-header">
        </header>
        <main class = "header auth-main">
            <section class = "auth-section auth-main-left">
            </section>
            <section class = "auth-section auth-main-middle">
                <section class = "main-form">
                    <el-form class = "auth-form">
                        <el-input
                            type = "text"
                            v-model = "uid"
                            class = "auth-input"
                            prefix-icon = ""
                            minlength = "4"
                            maxlength = "32"
                            placeholder = "邮箱/手机号"
                            autofocus = "true"
                            tabindex = "1"
                            @blur = "hanldeUidBlur"
                            />
                        <div
                            class = "auth-send-captach"
                            :class = "{ 'auth-send-captach-show': showSendCaptcha }"
                            @click = "handleClickSendCaptcha"
                            >发送验证码</div>
                        <el-input
                            type = "text"
                            v-model = "captcha"
                            class = "auth-input"
                            prefix-icon = ""
                            minlength = "6"
                            max-length = "32"
                            placeholder = "验证码"
                            tabindex = "2"
                            />
                        <div class = "auth-err-message">{{ error }}</div>
                        <div class = "auth-forget">
                            <a href = "javascript:void(0)" tabindex = "5">忘记密码?</a>
                        </div>
                        <div class = "auth-button-container">
                            <el-button
                                id = "TencentCaptcha"
                                class = "auth-button"
                                data-appid="2083161919"
                                data-cbfn="tCaptchaCallback"
                                :loading = "loadingRegister"
                                :class = "{'button-active': !loginActive}"
                                :disabled = "disableRegister"
                                tabindex = "4"
                                @click = "hanldeClickRegister"
                                >
                                注册
                            </el-button>
                            <el-button
                                id = "auth-login"
                                class = "auth-button"
                                :loading = "loadingLogin"
                                :disabled = "disableLogin"
                                tabindex = "3"
                                :class = "{'button-active': loginActive}"
                                @click = "hanldeClickLogin">
                                登录
                            </el-button>
                        </div>
                    </el-form>
                </section>
                <section class = "third-part">
             <!--        <ul>
                        <li>QQ</li>
                        <li>Github</li>
                    </ul> -->
                </section>
            </section>
            <section class = "auth-section auth-main-right">
            </section>
        </main>
        <footer class = "footer auth-footer">
        </footer>
    </div>
</template>

<script>

import Vue from 'vue';
import { mapState } from 'vuex';
import Url from '@/constants/url';
import Backend from '@/services/backend';
import { Form, Button, Input } from 'element-ui';


Vue.component(Form.name, Form);
Vue.component(Input.name, Input);
Vue.component(Button.name, Button);

export default {
    data: () => {
        return {
            uid: '',
            captcha: '',
            login: 'login',
            register: 'register',
            currNav: 'register',
            error: '',
            navList: ['login', 'register'],
            loadingLogin: false,
            disableLogin: false,
            loadingRegister: false,
            disableRegister: false,
            tCaptchaScript: null,
            showSendCaptcha: false,
        };
    },
    methods: {
        hanldeClickNav (nav) {
            if (this.navList.find(item => item === nav)) {
                this.currNav = nav;
            }
        },
        hanldeClickLogin() {
            this.handleClickRegisterOrLogin(this.login);
        },
        hanldeClickRegister() {
            this.handleClickRegisterOrLogin(this.register);
        },
        handleClickRegisterOrLogin(auth) {

            if (!(this.uid && this.captcha)) {
                this.error = "请输入账号或验证码";
                setTimeout(() => { this.error = ''; }, 2000);
                return;
            }

            this.loadingLogin = true;
            this.disableRegister = true;

            const final = (error) => {
                this.error = error || '';
                this.loadingLogin = false;
                this.disableRegister = false;
            };

            const data = { auth, uid: this.uid, captcha: this.captcha };

            this.$store.dispatch({ type: `register`, data: { final, ...data } });
        },
        tCaptchaCallback(res) {
            // res（用户主动关闭验证码）= {ret: 2, ticket: null}
            // res（验证成功） = {ret: 0, ticket: "String", randstr: "String"}
            if (res && res.ret === 0) {

            }
        },
        hanldeUidBlur() {
            if (this.uid) {
                this.showSendCaptcha = true;
            }
        },
        async handleClickSendCaptcha() {

            if (this.uid) {
                const result = await Backend.post(Url.Auth.Captcha, {
                    target: this.uid,
                });

            } else {
                this.error = "请输入正确的邮箱账号/手机号";
            }
        }
    },
    computed: {
        ... mapState(['auth']),
        loginActive() {
            return this.currNav === this.login;
        },
    },
    mounted() {},

    created() {

        if (this.tCaptchaScript == null) {
            const url = 'https://ssl.captcha.qq.com/TCaptcha.js';

            const script = this.tCaptchaScript = document.createElement('script');

            script.src = url;

            document.body.appendChild(script);
        }
    },
    beforeDestroy() {
        document.body.removeChild(this.tCaptchaScript);
        this.tCaptchaScript = null;
    }
};
</script>

<style lang="css">

.container {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: absolute;
    font-size: 0.16rem;
    /*background-image: url('../../static/images/bg.png');*/
    background: #ECE9E6;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #FFFFFF, #ECE9E6);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #FFFFFF, #ECE9E6); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.auth-header .auth-main .auth-footer {
    position: relative;
}

.auth-main {
    height: 100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row-reverse;
}

.auth-main-left {
    flex: none;
}

.auth-main-middle {
    flex: 2 1 4rem;
    padding: 1.2rem 0 1.8rem;
    box-sizing: border-box;
}

.auth-main-right {
    flex: 1 1 2rem;
}

.auth-nav {
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
}

.auth-nav a {
    color: inherit;
    box-sizing: border-box;
    text-decoration : none;
    padding: 0.5rem;
}

.main-form {
    height: 100%;
}

.auth-form {
    height: 100%;
    position: relative;
    padding: 0.5rem;
    box-sizing: border-box;
}

.auth-input, .auth-button-container, .auth-forget, .auth-send-captach, .auth-err-message {
    margin: auto;
    width: 4.6rem;
    display: block;
    box-sizing: border-box;
}

.auth-input {
    margin: 0.4rem auto 0;
}

.auth-send-captach {
    text-align: right;
    padding: 0.08rem 0.08rem 0;
    cursor: pointer;
    color: transparent;
}

.auth-send-captach.auth-send-captach-show {
    color: #00A1D6;
}

.auth-err-message {
    color: red;
    text-align: right;
    height: 0.16rem;
    line-height: 0.16rem;
    padding: 0.06rem 0.08rem 0;
}

.auth-forget {
    color: #00a1d6;
    text-align: right;
    margin: 0.2rem auto 0;
    padding: 0 0.08rem;
}

.auth-button-container {
    display: flex;
    justify-content: space-between;
    margin: 0.08rem auto 0;
}

.auth-button {
    width: 2rem;
}

a {
    color: inherit;
    text-decoration: none;
}

a:hover {
    cursor: pointer;
}

@media screen and (max-width: 750px) {
    .auth-main-right {
        display: none;
    }
}

</style>
