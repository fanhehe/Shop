import Backend from '@/services/backend';

export default {

    async user_auth({ commit }, payload) {
        const type = payload.type;
        const data = payload.data;

        const result = await Backend.get('/api/user/register/email', {
            email: payload.uid,
            password: payload.password,
        });

        if (data && data.final && typeof data.final === 'function') {
            data.final();
        }

        if (result && result.code === 0) {
            commit('fill', { id: 1, nick: 888 });
        } else {
            commit('fill', { id: 0, nick: 'empty'});
        }
    }
};
