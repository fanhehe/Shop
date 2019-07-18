import HttpUtil  from '@/utils/httputil';

export default class Backend extends HttpUtil {
    static getEndpoint() {
        return '127.0.0.1:10014';
    }
}
