var webpack = require('webpack');
var path = require("path");

module.exports = require('./scalajs.webpack.config');

module.exports.plugins = [
  new webpack.ProvidePlugin({
    $: "jquery",
    jQuery: "jquery",
    moment: "moment"
  })
];
