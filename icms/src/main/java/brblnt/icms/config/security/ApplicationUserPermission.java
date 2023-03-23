package brblnt.icms.config.security;

/**
 * Permissions for role.
 */
public enum ApplicationUserPermission {
  ADMIN_READ("admin:read"),
  ADMIN_WRITE("admin:write"),
  SUPER_USER_READ("suser:read"),
  SUPER_USER_WRITE("suser:write"),
  USER_READ("user:read"),
  USER_WRITE("user:write");

  private final String permission;

  ApplicationUserPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
