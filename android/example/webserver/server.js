// The PEM pass phrase is: password

var https = require('https');
var fs = require('fs');

var options = {
    // The Server's SSL Key
    key: fs.readFileSync('ssl/server.key'),
    // The Server's Cert
    cert: fs.readFileSync('ssl/server.crt'),
    rejectUnauthorized: false
};

https.createServer(options, function (req, res) {
        res.writeHead(200, {"Content-Type":"application/json"});
        res.end('{"status":"approved"}');
        console.log("Approved Client ", req.client.socket.remoteAddress);
}).listen(5678);
