import HttpUtil  from '@/utils/httputil';
import Config from '@/config/';

export default class Backend extends HttpUtil {

    static getScheme() {
        return Config.service.backend.scheme;
    }

    static getEndpoint() {
        return Config.service.backend.endpoint;
    }
}
