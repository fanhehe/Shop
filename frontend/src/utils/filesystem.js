import fs from 'fs';
import path from 'path';

export default class {

    static walk(dir) {
        let result = [];
        const files = fs.readdirSync(dir);

        files.forEach(function(item) {
            item = path.resolve(dir, item);
            const stat = fs.statSync(item);
            if (stat && stat.isDirectory()) {
                result = result.concat(this.walk(item));
            } else {
                result.push(item);
            }
        });

        return result;
    }

    static traversal(dir) {
        return this.walk(dir).map(item => path.relative(dir, item));
    }
};
