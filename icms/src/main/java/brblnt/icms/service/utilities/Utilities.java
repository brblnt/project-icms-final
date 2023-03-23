package brblnt.icms.service.utilities;

import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.service.exceptions.UserNotFoundException;
import brblnt.icms.service.modules.users.converter.creator.CompleteUserCreator;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.users.repository.utility.ApplicationUsersRepositoryUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


/**
 * Utility for Admin controller.
 * Static Strings for models and other common variables.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Utilities {

  private final ApplicationUsersRepositoryUtility applicationUsersRepositoryUtility;
  private final CompleteUserCreator completeUserCreator;

  //------------------------------------------------------------------------------------------------------------
  /**
   * Has any role.
   */
  //------------------------------------------------------------------------------------------------------------
  public static final String ADMIN_ROLE = "hasAnyRole('ROLE_ADMIN')";
  public static final String SUPER_USER_ROLE = "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER')";
  public static final String SIMPLE_USER_ROLE = "hasAnyRole('ROLE_ADMIN', 'ROLE_SIMPLE_USER')";
  //------------------------------------------------------------------------------------------------------------
  /**
   * Common variables.
   */
  //------------------------------------------------------------------------------------------------------------
  public static final String SUPER_USER = "SUPER_USER";
  public static final String SIMPLE_USER = "SIMPLE_USER";
  public static final String ADMIN = "ADMIN";
  public static final String PASSWORD_EXPIRE_NAME = "PWD_EXP_";
  public static final String INDEX = "index";
  //------------------------------------------------------------------------------------------------------------
  /**
   * URLs.
   */
  //------------------------------------------------------------------------------------------------------------
  //COMMON
  public static final String INDEX1 = "/";
  public static final String INDEX2 = INDEX1 + INDEX;
  public static final String HOME_URL = "/home";
  public static final String PROFILE_URL = "/profile";
  public static final String PAGE_URL = "/page";
  public static final String PROFILE_PAGE_URL = "profile-page";
  public static final String LIST_URL = "/list";
  public static final String NEW_URL = "/new";
  public static final String UPDATE_URL = "/update";
  public static final String ROOT_URL = "";
  public static final String ID_PATH_VARIABLE = "/{id}";
  public static final String CREATE_URL = "/create";
  public static final String DELETE_URL = "/{id}/delete";
  public static final String REMOVE_URL = "/remove/{id}";
  public static final String EDIT_URL = "/{id}/edit";
  public static final String API = "/api";
  //------------------------------------------------------------------------------------------------------------
  //ADMIN
  public static final String ADMIN_MAIN = "/admin";
  //------------------------------------------------------------------------------------------------------------
  //ADMIN SYSTEM DATABASE
  public static final String ADMIN_SYSTEM = ADMIN_MAIN + "/system";
  public static final String ADMIN_SYSTEM_DATABASE = ADMIN_SYSTEM + "/database";
  public static final String ADMIN_SYSTEM_RESTART = "/restart";
  //------------------------------------------------------------------------------------------------------------
  //ADMIN USER DATABASE
  public static final String ADMIN_USER = ADMIN_MAIN + "/user";
  //------------------------------------------------------------------------------------------------------------
  //SUPER USER
  public static final String SUPER_USER_MAIN = "/super-user";
  //------------------------------------------------------------------------------------------------------------
  //SUPER USER SETTINGS
  public static final String SUPER_USER_SETTINGS = SUPER_USER_MAIN + "/settings";
  public static final String SUPER_USER_BASICS = SUPER_USER_SETTINGS + "/basics";
  public static final String SUPER_USER_WORKERS = SUPER_USER_SETTINGS + "/workers";
  //USER
  public static final String USER_MAIN = "/user";
  //------------------------------------------------------------------------------------------------------------
  //MODULES
  //------------------------------------------------------------------------------------------------------------
  //ADDRESS
  public static final String ADDRESS_URL = "/address";
  //WORKSHEET
  public static final String WORKSHEET_URL = "/worksheet";
  public static final String WORKSHEET_CUSTOMER_URL = WORKSHEET_URL + "/customers";
  public static final String WORKSHEET_DEALER_URL = WORKSHEET_URL + "/dealers";
  public static final String WORKSHEET_FAULT_URL = WORKSHEET_URL + "/fault";
  public static final String WORKSHEET_OBJECT_URL = WORKSHEET_URL + "/items";
  public static final String WORKSHEET_PRODUCT_URL = WORKSHEET_URL + "/products";
  public static final String WORKSHEET_SERVICES_URL = WORKSHEET_URL + "/services";
  //WAREHOUSE
  public static final String WAREHOUSE_URL = "/warehouse";
  //------------------------------------------------------------------------------------------------------------
  /**
   * Redirects.
   */
  //------------------------------------------------------------------------------------------------------------
  public static final String PASSWORD_EXPIRED_REDIRECT = "redirect:/password/expired";
  public static final String LOGIN_REDIRECT = "redirect:/login";
  public static final String REDIRECT = "redirect:";
  //------------------------------------------------------------------------------------------------------------
  //ADMIN
  public static final String REDIRECT_ADMIN_SYSTEM_DATABASE = REDIRECT + ADMIN_SYSTEM_DATABASE;
  public static final String REDIRECT_ADMIN_USER_CREATE = REDIRECT + ADMIN_USER + CREATE_URL;
  public static final String REDIRECT_ADMIN_USER_LIST = REDIRECT + ADMIN_USER + LIST_URL;
  //------------------------------------------------------------------------------------------------------------
  //SUPER-USER
  public static final String REDIRECT_SUPER_USER_BASICS = REDIRECT + "/super-user/settings/basics";
  public static final String REDIRECT_SUPER_USER_WORKERS = REDIRECT + "/super-user/settings/workers";
  //------------------------------------------------------------------------------------------------------------
  //Modules
  public static final String REDIRECT_WORKSHEET_MAIN = REDIRECT + WORKSHEET_URL+"/list";
  public static final String REDIRECT_WORKSHEET_CUSTOMER = REDIRECT + WORKSHEET_CUSTOMER_URL;
  public static final String REDIRECT_WORKSHEET_DEALER = REDIRECT + WORKSHEET_DEALER_URL;
  public static final String REDIRECT_WORKSHEET_OBJECT = REDIRECT + WORKSHEET_OBJECT_URL;
  public static final String REDIRECT_WORKSHEET_PRODUCT = REDIRECT + WORKSHEET_PRODUCT_URL;
  public static final String REDIRECT_WORKSHEET_FAULT = REDIRECT + WORKSHEET_FAULT_URL;
  public static final String REDIRECT_WORKSHEET_SERVICE = REDIRECT + WORKSHEET_SERVICES_URL;
  //------------------------------------------------------------------------------------------------------------
  /**
   * Content types.
   */
  //------------------------------------------------------------------------------------------------------------
  //ADMIN
  public static final String ADMIN_THEME = "RED";
  public static final String USER_MANAGE = "user-manage";
  public static final String USER_CREATE = "user-create";
  public static final String USER_EDIT = "user-edit";
  public static final String DATABASE = "database";
  //------------------------------------------------------------------------------------------------------------
  //SUPER_USER
  public static final String BASIC_INFORMATION_SETTINGS = "basic-information-settings";
  public static final String SUPER_USER_COMPANY = "company";
  public static final String SUPER_USER_WORKERS_CONTENT = "workers";
  public static final String SUPER_USER_ACTUAL_WORKER = "actualWorker";
  //------------------------------------------------------------------------------------------------------------
  //USER
  //------------------------------------------------------------------------------------------------------------
  //MODULES
  public static final String WORKSHEET_MODULE = "worksheet-module";
  public static final String WORKSHEET_CUSTOMER_CONTENT = "customer";
  public static final String WORKSHEET_FAULTS_CONTENT = "fault";
  public static final String WORKSHEET_OBJECTS_CONTENT = "object";
  public static final String WORKSHEET_DEALERS_CONTENT = "dealer";
  public static final String WORKSHEET_PRODUCTS_CONTENT = "product";
  public static final String WORKSHEET_SERVICES_CONTENT = "service";
  public static final String WAREHOUSE_MODULE = "warehouse-module";
  //------------------------------------------------------------------------------------------------------------
  //COMMON
  public static final String PROFILE_PAGE = "profile-page";
  public static final String LIST_CONTENT = "list";
  public static final String NEW_CONTENT = "new";
  //------------------------------------------------------------------------------------------------------------
  /**
   * Models.
   */
  //------------------------------------------------------------------------------------------------------------
  //ADMIN
  public static final String ALL_USER = "allUser";
  public static final String USER_EDIT_MODEL = "userEdit";
  public static final String DB_URL = "urlDB";
  public static final String DB_USERNAME = "usernameForDB";
  public static final String DB_PW = "passwordForDB";
  //------------------------------------------------------------------------------------------------------------
  //SUPER_USER
  public static final String SUPER_USER_CONTENT = "superUserContent";
  //------------------------------------------------------------------------------------------------------------
  //USER
  //------------------------------------------------------------------------------------------------------------
  //MODULES
  //------------------------------------------------------------------------------------------------------------
  //WORKSHEET
  public static final String WORKSHEET_CONTENT = "worksheetContent";
  public static final String WORKSHEET_MODEL_CUSTOMERS = "customers";
  public static final String WORKSHEET_MODEL_ALL_CUSTOMERS = "allCustomer";
  public static final String WORKSHEET_MODEL_DEALERS = "dealers";
  public static final String WORKSHEET_MODEL_FAULTS = "faults";
  public static final String WORKSHEET_MODEL_ALL_FAULTS = "allFault";
  public static final String WORKSHEET_MODEL_OBJECTS = "objects";
  public static final String WORKSHEET_MODEL_ITEMS = "items";
  public static final String WORKSHEET_MODEL_PRODUCTS = "products";
  public static final String WORKSHEET_MODEL_SERVICES = "services";
  public static final String WORKSHEET_MODEL_WORKERS = "workers";
  public static final String WORKSHEET_MODEL_WORKSHEETS = "worksheets";
  public static final String WORKSHEET_MODEL_ACTUAL_WORKSHEET = "actualWorksheet";
  public static final String WORKSHEET_MODEL_ACTUAL_CUSTOMER = "actualCustomer";
  public static final String WORKSHEET_MODEL_ACTUAL_FAULT = "actualFault";
  public static final String WORKSHEET_MODEL_ACTUAL_OBJECT = "actualObject";
  public static final String WORKSHEET_MODEL_ACTUAL_PRODUCT = "actualProduct";
  public static final String WORKSHEET_MODEL_ACTUAL_SERVICE = "actualService";
  public static final String WORKSHEET_MODEL_ACTUAL_DEALER = "actualDealer";
  public static final String WORKSHEET_MODEL_PRODUCT_DEALER = "productDealers";
  public static final String WORKSHEET_MODEL_OBJECT_CUSTOMER = "objectsCustomers";

  //WAREHOUSE
  public static final String WAREHOUSE_CONTENT = "warehouseContent";
  //------------------------------------------------------------------------------------------------------------
  //COMMON
  public static final String MODEL_THEME = "theme";
  public static final String MODEL_TOPBAR = "userTopBar";
  public static final String MODEL_CONTENT = "content";
  public static final String ROLE_FOR_CONTENT = "loadRole";
  public static final String TOAST_INVOKE = "toastInvoke";
  //------------------------------------------------------------------------------------------------------------
  /**
   * Messages.
   */
  //------------------------------------------------------------------------------------------------------------
  //ATTRIBUTES
  public static final String SUCCESS = "success";
  public static final String MESSAGE_LINE_ONE = "message";
  public static final String MESSAGE_LINE_TWO = "messageLineTwo";
  //------------------------------------------------------------------------------------------------------------
  //MESSAGES
  public static final String MESSAGE_SUCCESS = "Sikeres művelet végrehajtás!";
  public static final String MESSAGE_UNSUCCESS = "Sikertelen művelet!";
  public static final String MISSING_INFORMATION = "Hiányzó adatok!";
  public static final String MESSAGE_PW_NOT_MATCH = "A jelszavak nem eggyeznek meg!";
  public static final String MESSAGE_USERNAME = "A felhasználónév már foglalt!";
  //------------------------------------------------------------------------------------------------------------
  //LOGIN
  public static final String LOGIN_URL = "/login";
  public static final String LOGIN_HELP = "/help";
  public static final String LOGIN_FAILED = "/failed";
  public static final String LOGIN_PAGE = "login-section/login";
  public static final String LOGIN_HELP_PAGE = "login-section/forgot-password";
  public static final String LOGIN_FAILED_PAGE = "login-section/login-unsuccessful";
  //------------------------------------------------------------------------------------------------------------
  //PASSWORD
  public static final String PASSWORD_URL = "/password";
  public static final String PASSWORD_EXPIRED = "/expired";
  public static final String PASSWORD_EXPIRED_PAGE = "login-section/change-password";
  //------------------------------------------------------------------------------------------------------------
  //TOAST
  public static final String TOAST_URL = "/toast";
  public static final String TOAST_PATH = "/{type}/{title}/{small}/{text}/{color}";
  public static final String TOAST_TYPE = "toastType";
  public static final String TOAST_COLOR = "toastColor";
  public static final String TOAST_HEADER = "toastHeader";
  public static final String TOAST_MESSAGE = "toastMessage";
  public static final String TOAST_SMALL = "toastSmall";
  public static final String TOAST_FRAGMENT = "common-fragments/toast :: toast";
  public static final String TOAST_SUCCESSFUL_UPDATE = "successful-toast-update";
  public static final String TOAST_UNSUCCESSFUL_UPDATE = "unsuccessful-toast-update";
  public static final String TOAST_SUCCESSFUL_DELETE = "successful-toast-delete";
  public static final String TOAST_UNSUCCESSFUL_DELETE = "unsuccessful-toast-delete";
  public static final String TOAST_SUCCESSFUL_SAVE_COMPANY = "successful-toast-save-company";
  public static final String TOAST_UNSUCCESSFUL_SAVE_COMPANY = "unsuccessful-toast-save-company";
  public static final String TOAST_CUSTOM_CODE_NOT_UNIQUE = "unsuccessful-toast-workers-update";
  /*
   * Common methods.
   */
  //------------------------------------------------------------------------------------------------------------

  /**
   * Get Complete User by authentication.
   */
  public CompleteUser getApplicationUser(Authentication authentication) {
    ApplicationUserJPA actualUser = null;
    try {
      actualUser = applicationUsersRepositoryUtility.getUserByUsername(authentication.getName());
      if (applicationUsersRepositoryUtility.passwordExpired(actualUser.getUserName())) {
        CompleteUser passwordExpiredUser = new CompleteUser();
        passwordExpiredUser.setUserName(PASSWORD_EXPIRE_NAME);
        return passwordExpiredUser;
      }
    } catch (UserNotFoundException e) {
      log.error("There is no user with username: {}", authentication.getName());
    }
    return completeUserCreator.createById(actualUser.getId());
  }

  /**
   * Get role based content type.
   */
  public String content(Authentication authentication) {
    String content = SIMPLE_USER;
    try {
      content = applicationUsersRepositoryUtility.getUserByUsername(authentication.getName()).getRole();
      if (content.equals(ADMIN)) {
        content = SUPER_USER;
      }
    } catch (UserNotFoundException e) {
      log.error("There is no user with username: {}", authentication.getName());
    }
    return content;
  }

  /**
   * Get home path if user has admin role change the path to super user.
   */
  public String getHomePath(Authentication authentication) {
    CompleteUser actualUser = getApplicationUser(authentication);
    String homePath = actualUser.getRole().toLowerCase();
    if (homePath.equals("admin")) {
      homePath = "super-user";
    }
    if (homePath.equals("super_user")) {
      homePath = "super-user";
    }
    if (homePath.equals("simple_user")) {
      homePath = "user";
    }
    return homePath;
  }

  /**
   * Get home path by role, correct super-user path.
   */
  public String getHomePathSuperUser(Authentication authentication) {
    CompleteUser actualUser = getApplicationUser(authentication);
    String homePath = actualUser.getRole().toLowerCase();
    if (homePath.equals("super_user")) {
      homePath = "super-user";
    }
    if (homePath.equals("simple_user")) {
      homePath = "user";
    }
    return homePath;
  }

  /**
   * Get toast message by custom code.
   */
  public String getMessage(String code) {
    if (code.equals("worksheetCustomerNewUserInfo")) {
      return "A kitöltött adatlapot a végén el kell menteni!";
    }
    if (code.equals("worksheetCustomerUpdateSuccessful")) {
      return "Sikeres módosítás!";
    }
    if (code.equals("worksheetCustomerUpdateUnSuccessful")) {
      return "Sikertelen módosítás!";
    }
    if (code.equals("noSelected")) {
      return "Nincs kiválasztott elem!";
    }
    if (code.equals("successfulDelete")) {
      return "Sikeres törlés!";
    }
    if (code.equals("unsuccessfulDelete")) {
      return "Sikertelen törlés!";
    }
    if (code.equals("companySaveSuccessful")) {
      return "Sikeresen frissítetted az adatokat!";
    }
    if (code.equals("companySaveUnsuccessful")) {
      return "Sikertelen frissítés!";
    }
    if (code.equals("customIdNotUnique")) {
      return "Az egyedi azonosító már foglalt!";
    }
    if (code.equals("na")) {
      return "";
    }
    return "na";
  }

}
