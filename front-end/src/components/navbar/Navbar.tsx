import styles from "./navbar.module.scss";

type NavbarProps = {
  children: React.ReactNode;
}

export const Navbar = ({ children }: NavbarProps) => {
  return (
    <nav className={styles.nav}>
        <p>Book Market</p>
      {children}
    </nav>
  );
}
