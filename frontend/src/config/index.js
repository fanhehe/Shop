let exp = {};
const env = process.env.NODE_ENV || 'development';

switch (env) {
    case 'testing':
        exp = require('./testing');
        break;
    case 'production':
        exp = require('./production');
        break;
    case 'development': default:
        exp = require('./development');
        break;
}

export default exp;
