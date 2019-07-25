import Type from '@/utils/type';
import Url from '@/constants/url';
import Backend from '@/services/backend';

export default {

    async register({ commit }, payload) {

        const type = payload.type;
        const data = payload.data;

        const result = await Backend.post(Url.Auth.Register, {
            target: data.uid,
            captcha: data.captcha,
        });

        const error = result.message || '';

        if (data && Type.isFunction(data.final)) {
            data.final(error);
        }

        if (result && result.code === 0) {
            commit('fill', { id: 1, nick: 888 });
        } else {
            commit('fill', { id: 0, nick: 'empty'});
        }
    }
};
