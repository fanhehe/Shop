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

        const options = Object.assign({
            mode: "cors",
            method: method,
            credentials: 'include',
            headers: Object.assign({}, headers);
        }, options);

        switch(method.toUpperCase()) {
            case 'GET':
                path = `${path}?${qs.encode(params)}`;
                break;
            case 'POST':
                options.body = qs.encode(params);
                break;
            case 'JSON':
                options.method = 'POST';
                options.body = JSON.stringify(params);
                options.headers['Content-Type'] = 'application/json';
                break;
            default:
                throw new Error("使用了不支持的方法");
        }

        return await new Promise((resolve, reject) => {
            fetch(`${this.getScheme()}${this.getEndpoint()}${path}`, options).then(async result => {
                resolve(await result.text());
            }).then(json => JSON.parse(json));
        });
    }
}
