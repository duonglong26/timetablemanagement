export const login = (account) => {
    let axios = require('axios');
    let config = {
        method: 'post',
        url: '/api/login',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: account
    };
    return axios(config);
};