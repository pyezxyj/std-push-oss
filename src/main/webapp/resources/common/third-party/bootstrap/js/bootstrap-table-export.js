/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * extensions: https://github.com/kayalshri/tableExport.jquery.plugin
 */

(function ($) {
    'use strict';
    var sprintf = $.fn.bootstrapTable.utils.sprintf;

    var TYPE_NAME = {
        json: 'JSON',
        xml: 'XML',
        png: 'PNG',
        csv: 'CSV',
        txt: 'TXT',
        sql: 'SQL',
        doc: 'MS-Word',
        excel: 'MS-Excel',
        powerpoint: 'MS-Powerpoint',
        pdf: 'PDF'
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showExport: true,
        exportDataType: 'all', // basic, all, selected
        // 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf'
        exportTypes: ['excel'],
        exportOptions: {},
        fileName: '表格导出'
    });

    $.extend($.fn.bootstrapTable.defaults.icons, {
        export: 'glyphicon-export icon-share'
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initToolbar = BootstrapTable.prototype.initToolbar;

    BootstrapTable.prototype.initToolbar = function () {
        this.showToolbar = this.options.showExport;

        _initToolbar.apply(this, Array.prototype.slice.apply(arguments));

        if (this.options.showExport) {
            var that = this,
                $btnGroup = this.$toolbar.find('>.btn-group'),
                $export = $btnGroup.find('div.export');

            if (!$export.length) {
                $export = $([
                    '<div class="export btn-group">',
                        '<button style="display:none;" class="btn' +
                            sprintf(' btn-%s', this.options.buttonsClass) +
                            sprintf(' btn-%s', this.options.iconSize) +
                            '" ' +
                            'data-type="excel" type="button">',
                            sprintf('<i class="%s %s"></i> ', this.options.iconsPrefix, this.options.icons.export),
                        '导出</button>' +
                    '</div>'].join('')).appendTo($btnGroup);

                var $menu = $export.find('button'),
                    exportTypes = this.options.exportTypes;

                if (typeof this.options.exportTypes === 'string') {
                    var types = this.options.exportTypes.slice(1, -1).replace(/ /g, '').split(',');

                    exportTypes = [];
                    $.each(types, function (i, value) {
                        exportTypes.push(value.slice(1, -1));
                    });
                }

                $menu.click(function () {
                    var type = $(this).data('type'),
                        doExport = function () {
                            that.$el.tableExport($.extend({}, that.options.exportOptions, {
                                type: type,
                                escape: false
                            }));
                        };

                    if (that.options.exportDataType === 'all' && that.options.pagination) {
                        that.$el.one(that.options.sidePagination === 'server' ? 'post-body.bs.table' : 'page-change.bs.table', function () {
                            doExport();
                            that.togglePagination();
                        });
                        that.togglePagination();
                    } else if (that.options.exportDataType === 'selected') {
                        var data = that.getData(),
                            selectedData = that.getAllSelections();

                        that.load(selectedData);
                        doExport();
                        that.load(data);
                    } else {
                        doExport();
                    }
                });
            }
        }
    };
})(jQuery);
