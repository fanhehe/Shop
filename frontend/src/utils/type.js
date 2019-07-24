const ex = {};

[
    'Null',
    'Date',
    'Number',
    'String',
    'RegExp',
    'Promise',
    'Function',
    'Undefined',
].forEach(item => {
    ex[`is${item}`] = (obj) => {
        return Object.prototype.toString.call(obj) === `[object ${item}]`;
    };
});


export default ex;
