
export default {

    async fill(state, payload, force = false) {
        for(let key in payload) {
            if (payload.hasOwnProperty(key)) {
                if (force || state[key] !== undefined) {
                    state[key] = payload[key];
                }
            }
        }
    }
};
