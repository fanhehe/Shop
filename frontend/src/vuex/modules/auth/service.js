import Type from '@/utils/type';
import Url from '@/vuex/common/url';
import Backend from '@/services/backend';

export default class Service {

    static async getSalt() {
        const result = await Backend.get(Url.Auth.Salt);

        if (result && result.code === 0) {
            return result.data.salt;
        }
    }

    static async doLogin() {

    }

    static async doLogout() {

    }

    static async doRegister() {

    }
}
