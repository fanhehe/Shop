import Type from '@/utils/type';
import Url from '@/vuex/common/url';
import Backend from '@/services/backend';


export default {

    async user_auth({ commit }, payload) {
        const type = payload.type;
        const data = payload.data;

        const result = await Backend.get(Url.Auth.Register, {
            email: payload.uid,
            password: payload.password,
        });

        if (data && Type.isFunction(data.final)) {
            data.final();
        }

        if (result && result.code === 0) {
            commit('fill', { id: 1, nick: 888 });
        } else {
            commit('fill', { id: 0, nick: 'empty'});
        }
    }
};
