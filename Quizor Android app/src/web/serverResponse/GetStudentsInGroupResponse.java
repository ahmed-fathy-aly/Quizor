package web.serverResponse;

import java.util.ArrayList;

import core.StudentInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class GetStudentsInGroupResponse.
 */
public class GetStudentsInGroupResponse extends ServerResponse
{
	/* fields */
	/** The student infos. */
	ArrayList<StudentInfo> studentInfos;

	/* constructors */
	/**
	 * Instantiates a new gets the students in group response.
	 *
	 * @param studentInfos the student infos
	 */
	public GetStudentsInGroupResponse(ArrayList<StudentInfo> studentInfos)
	{
		super();
		this.studentInfos = studentInfos;
	}

	/**
	 * Instantiates a new gets the students in group response.
	 */
	public GetStudentsInGroupResponse()
	{
		super();
		studentInfos = new ArrayList<>();
	}

	/* getters and setters */
	/**
	 * Gets the student infos.
	 *
	 * @return the student infos
	 */
	public ArrayList<StudentInfo> getStudentInfos()
	{
		return studentInfos;
	}

	/**
	 * Sets the student infos.
	 *
	 * @param studentInfos the new student infos
	 */
	public void setStudentInfos(ArrayList<StudentInfo> studentInfos)
	{
		this.studentInfos = studentInfos;
	}

}
