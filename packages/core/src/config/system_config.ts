import { APITipConfigInterface, Tips } from 'config/api_tip_config.interface';
import apiTipConfigJson from './api_tip_config.auto.json';
import systemConfigJson from './system_config.auto.json';
import { SystemConfigInterfaceNotifications, SystemConfigInterface, Templates, Types } from './system_config.interface';

/**
  * config class, directly get the entire class of SystemConfig
  */
const SystemConfig: SystemConfigInterface = systemConfigJson as SystemConfigInterface; 

/**
 * api tip config class
 */
const ApiTipConfig = apiTipConfigJson as APITipConfigInterface;

/**
  * Settings object, quickly get system_config.system table, system constant configuration
  *
  * pass in key
 * @example Conf.api_rate...
 */
const Settings = SystemConfig.settings;

// notification
const jsonToObject = (object: object) => {
  const obj = { ...object };
  Object.keys(object).forEach((i) => {
    obj[i] = i;
  });
  return obj;
};
const Notifications = (SystemConfig.notifications as any) as SystemConfigInterfaceNotifications;

const NotificationTypes = Notifications.types;
const NotificationTemplates = Notifications.templates;

const NoticeTypesConstant = jsonToObject(Notifications.types) as {
  [key in keyof Types]: key;
};
const NoticeTemplatesConstant = jsonToObject(Notifications.templates) as {
  [key in keyof Templates]: key;
};
const ApiTipConstant = jsonToObject(ApiTipConfig.api.tips) as {
  [key in keyof Tips]: key;
};

export {
  SystemConfig,
  Settings,
  NotificationTypes,
  NotificationTemplates,
  NoticeTypesConstant,
  NoticeTemplatesConstant,
  ApiTipConfig,
  ApiTipConstant,
};
