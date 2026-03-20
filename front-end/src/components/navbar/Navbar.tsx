/* eslint-disable @typescript-eslint/no-unsafe-member-access */
import { useGetCurrentUserQuery } from "../../app/auth/authApiSlice";
import styles from "./navbar.module.scss";

type NavbarProps = {
  children: React.ReactNode;
}

export const Navbar = ({ children }: NavbarProps) => {

  // eslint-disable-next-line @typescript-eslint/no-unsafe-assignment
  const { data: currentUser} = useGetCurrentUserQuery({});

  return (
    <nav className={styles.nav}>
     {currentUser && <h1>Hello {currentUser.firstName}</h1>}
        <p>Book Market</p>
      {children}
    </nav>
  );
}
