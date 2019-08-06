import React, { createContext, useMemo } from 'react/index';
import { DataSet } from 'choerodon-ui/pro';
import { inject } from 'mobx-react';
import { injectIntl } from 'react-intl';
import SendSettingDataSet from './SendSettingDataSet';
import TemplateDataSet from './TemplateDataSet';

const Store = createContext();

export default Store;

export const StoreProvider = injectIntl(inject('AppState')(
  (props) => {
    const { AppState: { currentMenuType: { type, id } }, intl, children } = props;
    const { settingId, settingBusinessType, settingType } = props.match.params;
    const intlPrefix = 'global.notifyContent';
    const sendSettingDataSet = useMemo(() => new DataSet(SendSettingDataSet(settingId, intl, `${intlPrefix}.sendSetting`)));
    const templateDataSet = useMemo(() => new DataSet(TemplateDataSet(settingId, settingBusinessType, settingType, 'query', intl, `${intlPrefix}.template`)));
    const createTemplateDataSet = useMemo(() => new DataSet(TemplateDataSet(settingId, settingBusinessType, settingType, 'create', intl, `${intlPrefix}.template`)));
    const value = {
      ...props,
      settingId,
      settingType,
      intlPrefix,
      createTemplateDataSet,
      sendSettingDataSet,
      templateDataSet,
    };
    return (
      <Store.Provider value={value}>
        {children}
      </Store.Provider>
    );
  },
));
