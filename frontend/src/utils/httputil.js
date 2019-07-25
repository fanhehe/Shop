import qs from 'querystring';

export default class HttpUtil {

    static getScheme() {
        return 'http://';
    }

    static getEndpoint() {
        throw new Error('类需要被继承');
    }

    static async get(path, params = {}, headers = {}, options = {}) {
        return await this.call(path, params, headers, options, 'GET');
    }

    static async post(path, params = {}, headers = {}, options = {}) {
        return await this.call(path, params, headers, options, 'POST');
    }

    static async json(path, params = {}, headers = {}, options = {}) {
        return await this.call(path, params, headers, options, 'JSON');
    }

    static async call(path, params = {}, headers = {}, options = {}, method = 'GET') {

        options = Object.assign({
            mode: "cors",
            method: method,
            credentials: 'include',
            headers: Object.assign({}, headers),
        }, options);

        switch(method.toUpperCase()) {
            case 'GET':
                path = `${path}?${qs.encode(params)}`;
                break;
            case 'POST':
                options.body = qs.encode(params);
                options.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
                break;
            case 'JSON':
                options.method = 'POST';
                options.body = JSON.stringify(params);
                options.headers['Content-Type'] = 'application/json';
                break;
            default:
                throw new Error("使用了不支持的方法");
        }

        return await Promise.race([this.makeRequestPromise(path, options), this.makeTimeoutPromise(5000)]);
    }

    static makeRequestPromise(path, options) {
        return new Promise(resolve => {

            const url = `${this.getScheme()}${this.getEndpoint()}${path}`;

            fetch(url, options)
                .then(async result => {
                    resolve(JSON.parse(await result.text()));
                })
                .catch(err => (resolve({
                    code: 500,
                    message: '网络异常',
                })));
        });
    }

    static makeTimeoutPromise(timeout) {
        return new Promise((r, rj) => {
            setTimeout(() => {
                rj(timeout);
            }, timeout);
        }).catch(timeout => ({
            code: 500,
            message: '访问超时,请稍后再试',
            data: {
                timeout,
            }
        }));
    }
}
