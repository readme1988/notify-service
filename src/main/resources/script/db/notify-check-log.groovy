package script.db

databaseChangeLog(logicalFilePath: 'script/db/notify-check-log.groovy') {
    changeSet(author: 'scp', id: '2019-12-11-add-notify-check-log') {
        createTable(tableName: "notify_check_log", remarks: 'upgrade log') {
            column(name: 'id', type: 'BIGINT UNSIGNED', remarks: '主键，ID', autoIncrement: true) {
                constraints(primaryKey: true)
            }
            column(name: 'begin_check_date', type: 'DATETIME', remarks: '开始处理时间')
            column(name: 'end_check_date', type: 'DATETIME', remarks: '结束处理时间')
            column(name: 'log', type: 'MEDIUMTEXT', remarks: '内容')
            column(name: "object_version_number", type: "BIGINT UNSIGNED", defaultValue: "1")
            column(name: "created_by", type: "BIGINT UNSIGNED", defaultValue: "0")
            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "BIGINT UNSIGNED", defaultValue: "0")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}