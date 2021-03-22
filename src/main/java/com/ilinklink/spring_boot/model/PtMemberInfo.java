package com.ilinklink.spring_boot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员信息
 * </p>
 *
 * @author chuck
 * @since 2021-03-22
 */
@Data
@ApiModel(value="PtMemberInfo对象", description="会员信息")
public class PtMemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;


/**
自动生成pt_member_info表相关的，mybatis里的insert语句:

  void insert(PtMemberInfo params);

  <insert id="insert" parameterType="com.chuck.core.dao.entity.PtMemberInfo">
    INSERT INTO
      pt_member_info(
         Member_ID,
         Member_Uid,
         Agent_Level,
         Agent_Level_UTime,
         Agent_Province,
         Agent_City,
         Agent_Area,
         Active_Code,
         Hicoin_Register,
         Invite_Code,
         Pay_Pwd,
         Pay_Pwd_Salt,
         Pay_Pwd_UTime,
         Member_Account,
         Member_Pwd,
         Member_Pwd_Salt,
         Pwd_UTime,
         Member_Nickname,
         Member_Gender,
         Member_Age,
         Member_Avatar,
         Member_Coupon_Balance,
         Balance_UTime,
         First_Member,
         Acc_Status,
         Acc_Status_UTime,
         Trade_Status,
         Trade_Status_UTime,
         Member_Remove,
         Remove_Time,
         CTime,
         UTime
      )
      VALUES
      (
          #{memberId,jdbcType=VARCHAR},
          #{memberUid,jdbcType=VARCHAR},
          #{agentLevel,jdbcType=INTEGER},
          #{agentLevelUtime,jdbcType=TIMESTAMP},
          #{agentProvince,jdbcType=VARCHAR},
          #{agentCity,jdbcType=VARCHAR},
          #{agentArea,jdbcType=VARCHAR},
          #{activeCode,jdbcType=VARCHAR},
          #{hicoinRegister,jdbcType=INTEGER},
          #{inviteCode,jdbcType=VARCHAR},
          #{payPwd,jdbcType=VARCHAR},
          #{payPwdSalt,jdbcType=VARCHAR},
          #{payPwdUtime,jdbcType=TIMESTAMP},
          #{memberAccount,jdbcType=VARCHAR},
          #{memberPwd,jdbcType=VARCHAR},
          #{memberPwdSalt,jdbcType=VARCHAR},
          #{pwdUtime,jdbcType=TIMESTAMP},
          #{memberNickname,jdbcType=VARCHAR},
          #{memberGender,jdbcType=INTEGER},
          #{memberAge,jdbcType=INTEGER},
          #{memberAvatar,jdbcType=VARCHAR},
          #{memberCouponBalance,jdbcType=INTEGER},
          #{balanceUtime,jdbcType=TIMESTAMP},
          #{firstMember,jdbcType=INTEGER},
          #{accStatus,jdbcType=INTEGER},
          #{accStatusUtime,jdbcType=TIMESTAMP},
          #{tradeStatus,jdbcType=INTEGER},
          #{tradeStatusUtime,jdbcType=TIMESTAMP},
          #{memberRemove,jdbcType=INTEGER},
          #{removeTime,jdbcType=TIMESTAMP},
          #{CTime,jdbcType=TIMESTAMP},
          #{UTime,jdbcType=TIMESTAMP}
      )
    </insert>

**/

/**
自动生成pt_member_info表相关的，mybatis里的批量insert语句:

   void insertList( @Param("list" )ArrayList<PtMemberInfo> params);

   <insert id="insertList" parameterType="java.util.List">
    INSERT INTO
      pt_member_info(
         Member_ID,
         Member_Uid,
         Agent_Level,
         Agent_Level_UTime,
         Agent_Province,
         Agent_City,
         Agent_Area,
         Active_Code,
         Hicoin_Register,
         Invite_Code,
         Pay_Pwd,
         Pay_Pwd_Salt,
         Pay_Pwd_UTime,
         Member_Account,
         Member_Pwd,
         Member_Pwd_Salt,
         Pwd_UTime,
         Member_Nickname,
         Member_Gender,
         Member_Age,
         Member_Avatar,
         Member_Coupon_Balance,
         Balance_UTime,
         First_Member,
         Acc_Status,
         Acc_Status_UTime,
         Trade_Status,
         Trade_Status_UTime,
         Member_Remove,
         Remove_Time,
         CTime,
         UTime
      )
    VALUES
      <foreach collection="list" item="item" separator=",">
      (
          #{item.memberId,jdbcType=VARCHAR},
          #{item.memberUid,jdbcType=VARCHAR},
          #{item.agentLevel,jdbcType=INTEGER},
          #{item.agentLevelUtime,jdbcType=TIMESTAMP},
          #{item.agentProvince,jdbcType=VARCHAR},
          #{item.agentCity,jdbcType=VARCHAR},
          #{item.agentArea,jdbcType=VARCHAR},
          #{item.activeCode,jdbcType=VARCHAR},
          #{item.hicoinRegister,jdbcType=INTEGER},
          #{item.inviteCode,jdbcType=VARCHAR},
          #{item.payPwd,jdbcType=VARCHAR},
          #{item.payPwdSalt,jdbcType=VARCHAR},
          #{item.payPwdUtime,jdbcType=TIMESTAMP},
          #{item.memberAccount,jdbcType=VARCHAR},
          #{item.memberPwd,jdbcType=VARCHAR},
          #{item.memberPwdSalt,jdbcType=VARCHAR},
          #{item.pwdUtime,jdbcType=TIMESTAMP},
          #{item.memberNickname,jdbcType=VARCHAR},
          #{item.memberGender,jdbcType=INTEGER},
          #{item.memberAge,jdbcType=INTEGER},
          #{item.memberAvatar,jdbcType=VARCHAR},
          #{item.memberCouponBalance,jdbcType=INTEGER},
          #{item.balanceUtime,jdbcType=TIMESTAMP},
          #{item.firstMember,jdbcType=INTEGER},
          #{item.accStatus,jdbcType=INTEGER},
          #{item.accStatusUtime,jdbcType=TIMESTAMP},
          #{item.tradeStatus,jdbcType=INTEGER},
          #{item.tradeStatusUtime,jdbcType=TIMESTAMP},
          #{item.memberRemove,jdbcType=INTEGER},
          #{item.removeTime,jdbcType=TIMESTAMP},
          #{item.CTime,jdbcType=TIMESTAMP},
          #{item.UTime,jdbcType=TIMESTAMP}
      )
      </foreach>
    </insert>

**/

/**
自动生成pt_member_info表相关的，mybatis里的update语句:

  void update(PtMemberInfo params);

  <update id="update" parameterType=""com.chuck.core.dao.entity.PtMemberInfo"">
    UPDATE
        pt_member_info
    SET
        <if test="memberId != null">
          Member_ID = #{memberId,jdbcType=VARCHAR},
        </if>
        <if test="memberUid != null">
          Member_Uid = #{memberUid,jdbcType=VARCHAR},
        </if>
        <if test="agentLevel != null">
          Agent_Level = #{agentLevel,jdbcType=INTEGER},
        </if>
        <if test="agentLevelUtime != null">
          Agent_Level_UTime = #{agentLevelUtime,jdbcType=TIMESTAMP},
        </if>
        <if test="agentProvince != null">
          Agent_Province = #{agentProvince,jdbcType=VARCHAR},
        </if>
        <if test="agentCity != null">
          Agent_City = #{agentCity,jdbcType=VARCHAR},
        </if>
        <if test="agentArea != null">
          Agent_Area = #{agentArea,jdbcType=VARCHAR},
        </if>
        <if test="activeCode != null">
          Active_Code = #{activeCode,jdbcType=VARCHAR},
        </if>
        <if test="hicoinRegister != null">
          Hicoin_Register = #{hicoinRegister,jdbcType=INTEGER},
        </if>
        <if test="inviteCode != null">
          Invite_Code = #{inviteCode,jdbcType=VARCHAR},
        </if>
        <if test="payPwd != null">
          Pay_Pwd = #{payPwd,jdbcType=VARCHAR},
        </if>
        <if test="payPwdSalt != null">
          Pay_Pwd_Salt = #{payPwdSalt,jdbcType=VARCHAR},
        </if>
        <if test="payPwdUtime != null">
          Pay_Pwd_UTime = #{payPwdUtime,jdbcType=TIMESTAMP},
        </if>
        <if test="memberAccount != null">
          Member_Account = #{memberAccount,jdbcType=VARCHAR},
        </if>
        <if test="memberPwd != null">
          Member_Pwd = #{memberPwd,jdbcType=VARCHAR},
        </if>
        <if test="memberPwdSalt != null">
          Member_Pwd_Salt = #{memberPwdSalt,jdbcType=VARCHAR},
        </if>
        <if test="pwdUtime != null">
          Pwd_UTime = #{pwdUtime,jdbcType=TIMESTAMP},
        </if>
        <if test="memberNickname != null">
          Member_Nickname = #{memberNickname,jdbcType=VARCHAR},
        </if>
        <if test="memberGender != null">
          Member_Gender = #{memberGender,jdbcType=INTEGER},
        </if>
        <if test="memberAge != null">
          Member_Age = #{memberAge,jdbcType=INTEGER},
        </if>
        <if test="memberAvatar != null">
          Member_Avatar = #{memberAvatar,jdbcType=VARCHAR},
        </if>
        <if test="memberCouponBalance != null">
          Member_Coupon_Balance = #{memberCouponBalance,jdbcType=INTEGER},
        </if>
        <if test="balanceUtime != null">
          Balance_UTime = #{balanceUtime,jdbcType=TIMESTAMP},
        </if>
        <if test="firstMember != null">
          First_Member = #{firstMember,jdbcType=INTEGER},
        </if>
        <if test="accStatus != null">
          Acc_Status = #{accStatus,jdbcType=INTEGER},
        </if>
        <if test="accStatusUtime != null">
          Acc_Status_UTime = #{accStatusUtime,jdbcType=TIMESTAMP},
        </if>
        <if test="tradeStatus != null">
          Trade_Status = #{tradeStatus,jdbcType=INTEGER},
        </if>
        <if test="tradeStatusUtime != null">
          Trade_Status_UTime = #{tradeStatusUtime,jdbcType=TIMESTAMP},
        </if>
        <if test="memberRemove != null">
          Member_Remove = #{memberRemove,jdbcType=INTEGER},
        </if>
        <if test="removeTime != null">
          Remove_Time = #{removeTime,jdbcType=TIMESTAMP},
        </if>
        <if test="CTime != null">
          CTime = #{CTime,jdbcType=TIMESTAMP},
        </if>
        <if test="UTime != null">
          UTime = #{UTime,jdbcType=TIMESTAMP}
        </if>
    WHERE
    1==1
   </update>
**/

/**
自动生成pt_member_info表相关的，mybatis里的查询语句:

  List<PtMemberInfo> queryList();

  <select id="queryList" resultType=""com.chuck.core.dao.entity.PtMemberInfo"">
    SELECT
      Member_ID  memberId,
      Member_Uid  memberUid,
      Agent_Level  agentLevel,
      Agent_Level_UTime  agentLevelUtime,
      Agent_Province  agentProvince,
      Agent_City  agentCity,
      Agent_Area  agentArea,
      Active_Code  activeCode,
      Hicoin_Register  hicoinRegister,
      Invite_Code  inviteCode,
      Pay_Pwd  payPwd,
      Pay_Pwd_Salt  payPwdSalt,
      Pay_Pwd_UTime  payPwdUtime,
      Member_Account  memberAccount,
      Member_Pwd  memberPwd,
      Member_Pwd_Salt  memberPwdSalt,
      Pwd_UTime  pwdUtime,
      Member_Nickname  memberNickname,
      Member_Gender  memberGender,
      Member_Age  memberAge,
      Member_Avatar  memberAvatar,
      Member_Coupon_Balance  memberCouponBalance,
      Balance_UTime  balanceUtime,
      First_Member  firstMember,
      Acc_Status  accStatus,
      Acc_Status_UTime  accStatusUtime,
      Trade_Status  tradeStatus,
      Trade_Status_UTime  tradeStatusUtime,
      Member_Remove  memberRemove,
      Remove_Time  removeTime,
      CTime  CTime,
      UTime  UTime
    FROM
      pt_member_info
    WHERE
      1==1
   </select>

**/





    /*****
        * 数据库原始字段
    *******/
    public static final String MEMBER_ID="Member_ID";
    public static final String MEMBER_UID="Member_Uid";
    public static final String AGENT_LEVEL="Agent_Level";
    public static final String AGENT_LEVEL_UTIME="Agent_Level_UTime";
    public static final String AGENT_PROVINCE="Agent_Province";
    public static final String AGENT_CITY="Agent_City";
    public static final String AGENT_AREA="Agent_Area";
    public static final String ACTIVE_CODE="Active_Code";
    public static final String HICOIN_REGISTER="Hicoin_Register";
    public static final String INVITE_CODE="Invite_Code";
    public static final String PAY_PWD="Pay_Pwd";
    public static final String PAY_PWD_SALT="Pay_Pwd_Salt";
    public static final String PAY_PWD_UTIME="Pay_Pwd_UTime";
    public static final String MEMBER_ACCOUNT="Member_Account";
    public static final String MEMBER_PWD="Member_Pwd";
    public static final String MEMBER_PWD_SALT="Member_Pwd_Salt";
    public static final String PWD_UTIME="Pwd_UTime";
    public static final String MEMBER_NICKNAME="Member_Nickname";
    public static final String MEMBER_GENDER="Member_Gender";
    public static final String MEMBER_AGE="Member_Age";
    public static final String MEMBER_AVATAR="Member_Avatar";
    public static final String MEMBER_COUPON_BALANCE="Member_Coupon_Balance";
    public static final String BALANCE_UTIME="Balance_UTime";
    public static final String FIRST_MEMBER="First_Member";
    public static final String ACC_STATUS="Acc_Status";
    public static final String ACC_STATUS_UTIME="Acc_Status_UTime";
    public static final String TRADE_STATUS="Trade_Status";
    public static final String TRADE_STATUS_UTIME="Trade_Status_UTime";
    public static final String MEMBER_REMOVE="Member_Remove";
    public static final String REMOVE_TIME="Remove_Time";
    public static final String CTIME="CTime";
    public static final String UTIME="UTime";


    @ApiModelProperty( value = "会员ID，唯一" , required = true)
    private String memberId;

    @ApiModelProperty( value = "第三方钱包服务的uid" , required = true)
    private String memberUid;

    @ApiModelProperty( value = "代理商等级 0-普通用户 1-区级代理 2-市级代理" , required = true)
    private Integer agentLevel;

    @ApiModelProperty( value = "代理商等级更新时间" , required = true)
    private Date agentLevelUtime;

    @ApiModelProperty( value = "市级代理省份代码(市级代理专属)" , required = true)
    private String agentProvince;

    @ApiModelProperty( value = "市级代理城市代码(市级代理专属)" , required = true)
    private String agentCity;

    @ApiModelProperty( value = "区级代理区代码(区级代理专属)" , required = true)
    private String agentArea;

    @ApiModelProperty( value = "激活码" , required = true)
    private String activeCode;

    @ApiModelProperty( value = "是否Hicion注册过 0-否 1-是" , required = true)
    private Integer hicoinRegister;

    @ApiModelProperty( value = "注册时填入的邀请码" , required = true)
    private String inviteCode;

    @ApiModelProperty( value = "支付密码" , required = true)
    private String payPwd;

    @ApiModelProperty( value = "支付密码salt" , required = true)
    private String payPwdSalt;

    @ApiModelProperty( value = "支付密码更新时间" , required = true)
    private Date payPwdUtime;

    @ApiModelProperty( value = "登录账号" , required = true)
    private String memberAccount;

    @ApiModelProperty( value = "密码" , required = true)
    private String memberPwd;

    @ApiModelProperty( value = "密码salt" , required = true)
    private String memberPwdSalt;

    @ApiModelProperty( value = "密码更新时间" , required = true)
    private Date pwdUtime;

    @ApiModelProperty( value = "会员昵称" , required = true)
    private String memberNickname;

    @ApiModelProperty( value = "会员性别 1-男、2-女" , required = true)
    private Integer memberGender;

    @ApiModelProperty( value = "会员年龄" , required = true)
    private Integer memberAge;

    @ApiModelProperty( value = "会员头像" , required = true)
    private String memberAvatar;

    @ApiModelProperty( value = "消费券余额" , required = true)
    private Integer memberCouponBalance;

    @ApiModelProperty( value = "余额更新时间" , required = true)
    private Date balanceUtime;

    @ApiModelProperty( value = "是否为首个注册的会员 0-否 1-是" , required = true)
    private Integer firstMember;

    @ApiModelProperty( value = "会员账号状态 1-正常、2-冻结" , required = true)
    private Integer accStatus;

    @ApiModelProperty( value = "账号状态更新时间" , required = true)
    private Date accStatusUtime;

    @ApiModelProperty( value = "交易状态 1-正常 2-冻结" , required = true)
    private Integer tradeStatus;

    @ApiModelProperty( value = "交易状态更新时间" , required = true)
    private Date tradeStatusUtime;

    @ApiModelProperty( value = "删除状态 0-未删除 1-已删除" , required = true)
    private Integer memberRemove;

    @ApiModelProperty( value = "删除时间" , required = true)
    private Date removeTime;

    @ApiModelProperty( value = "创建时间" , required = true)
    private Date CTime;

    @ApiModelProperty( value = "更新时间" , required = true)
    private Date UTime;



}
