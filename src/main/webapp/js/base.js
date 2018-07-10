var base_path = "http://localhost:8080/poms";
function Config(url, type, dataType, data, success, error) {
    this.url = url;
    this.type= type;
    this.dataType = dataType;
    this.data = data;
    this.success = success;
    this.error = error;
}
